package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class LruCache {

  static class IsbnInfo {
    IsbnInfo prev;
    IsbnInfo next;
    int key;
    int price;

    IsbnInfo(IsbnInfo prev, IsbnInfo next, int key, int price) {
      this.prev = prev;
      this.next = next;
      this.key = key;
      this.price = price;
    }
  }
  private Map<Integer, IsbnInfo> isbnCache;
  private int capacity;
  private IsbnInfo first;
  private IsbnInfo last;

  LruCache(final int capacity) {
    this.isbnCache = new HashMap<>(capacity);
    this.capacity = capacity;
  }

  public Integer lookup(Integer key) {
    if (isbnCache.containsKey(key)) {
      return getFirst(key).price;
    } else return -1;
  }

  private IsbnInfo getFirst(Integer key) {
    IsbnInfo node = isbnCache.get(key);
    if (node == first) {
      return node;
    } else if (node == last) {
      IsbnInfo tempLastPrev = node.prev;

      if (tempLastPrev != null) {
        tempLastPrev.next = null;
        node.prev = null;
      }

      node.next = first;
      first.prev = node;
      first = node;

      last = tempLastPrev;

      return first;
    } else {
      IsbnInfo tempNext = node.next;
      IsbnInfo tempPrev = node.prev;

      tempPrev.next = tempNext;
      tempNext.prev = tempPrev;

      node.next = first;
      first.prev = node;
      first = node;
      return first;
    }
  }

  public void insert(Integer key, Integer value) {
    if (!isbnCache.containsKey(key)) {
      if (isbnCache.size() == capacity) {
        isbnCache.remove(last.key);
        last = last.prev;
        if (last != null) {
          last.next = null;
        } else {
          first = null;
        }
      }
      IsbnInfo node = new IsbnInfo(null, null, key, value);
      if (first != null) {
        node.next = first;
        first.prev = node;
      }
      first = node;
      if (last == null) {
        last = node;
      }
      isbnCache.put(key, node);
    } else {
      getFirst(key);
    }
  }

  public Boolean erase(Object key) {
    if (isbnCache.containsKey(key)) {
      IsbnInfo node = isbnCache.remove(key);
      if (node == first) {
        first = first.next;
        if (first != null) {
          first.prev = null;
        } else {
          last = null;
        }
      } else if (node == last) {
        last = last.prev;
        if (last != null) {
          last.next = null;
        } else {
          first = null;
        }
      } else {
        IsbnInfo tempNext = node.next;
        IsbnInfo tempPrev = node.prev;

        tempPrev.next = tempNext;
        tempNext.prev = tempPrev;
      }
      return true;
    } else return false;
  }

  @EpiUserType(ctorParams = {String.class, int.class, int.class})
  public static class Op {
    String code;
    int arg1;
    int arg2;

    public Op(String code, int arg1, int arg2) {
      this.code = code;
      this.arg1 = arg1;
      this.arg2 = arg2;
    }
  }

  @EpiTest(testDataFile = "lru_cache.tsv")
  public static void runTest(List<Op> commands) throws TestFailure {
    if (commands.isEmpty() || !commands.get(0).code.equals("LruCache")) {
      throw new RuntimeException("Expected LruCache as first command");
    }
    LruCache cache = new LruCache(commands.get(0).arg1);
    for (Op op : commands.subList(1, commands.size())) {
      int result;
      switch (op.code) {
      case "lookup":
        result = cache.lookup(op.arg1);
        if (result != op.arg2) {
          throw new TestFailure("Lookup: expected " + String.valueOf(op.arg2) +
                                ", got " + String.valueOf(result));
        }
        break;
      case "insert":
        cache.insert(op.arg1, op.arg2);
        break;
      case "erase":
        result = cache.erase(op.arg1) ? 1 : 0;
        if (result != op.arg2) {
          throw new TestFailure("Erase: expected " + String.valueOf(op.arg2) +
                                ", got " + String.valueOf(result));
        }
        break;
      default:
        throw new RuntimeException("Unexpected command " + op.code);
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LruCache.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
