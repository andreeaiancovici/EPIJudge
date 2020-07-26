package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import javax.naming.spi.*;
import java.util.HashSet;
import java.util.Set;
public class DoListsOverlap {

  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                   ListNode<Integer> l1) {
    int c0 = hasCycleLength(l0);
    int c1 = hasCycleLength(l1);
    if (c0 != 0 && c1 != 0) {
      if (c0 == c1) {
        ListNode<Integer> temp = l0;
        while (c0 > 0) {
          temp = temp.next;
          c0--;
        }
        ListNode<Integer> start = l0;
        while (start != temp) {
          start = start.next;
          temp = temp.next;
        }
        return start;
      } else return null;
    } else if (c0 == 0 && c1 == 0) {
      int s0 = getLength(l0);
      int s1 = getLength(l1);
      int diff = s0 - s1;
      if (diff > 0) {
        while (diff > 0) {
          l0 = l0.next;
          diff--;
        }
      } else if (diff < 0) {
        while (diff < 0) {
          l1 = l1.next;
          diff++;
        }
      }
      while (l0 != null && l1 != null) {
        if (diff == 0) {
          if (l0 == l1) {
            return l0;
          }
          l0 = l0.next;
          l1 = l1.next;
        }
      }
      return null;
    } else {
      return null;
    }
  }

  private static int hasCycleLength(ListNode<Integer> l) {
    ListNode<Integer> oneStep = l;
    ListNode<Integer> twoStep = l;
    if (twoStep != null && twoStep.next != null) {
      twoStep = twoStep.next.next;
    }
    while (oneStep != null || (twoStep != null && twoStep.next != null)) {
      if (oneStep == twoStep) {
        twoStep = twoStep.next;
        int size = 1;
        while (twoStep != oneStep) {
          size++;
          if (twoStep != null) {
            twoStep = twoStep.next;
          }
        }
        return size;
      }
      if (oneStep != null) {
        oneStep = oneStep.next;
      }
      if (twoStep != null) {
        twoStep = twoStep.next;
        if (twoStep != null) {
          twoStep = twoStep.next;
        }
      }
    }
    return 0;
  }

  private static int getLength(ListNode<Integer> l) {
    int count = 0;
    while(l != null) {
      count++;
      l = l.next;
    }
    return count;
  }

  @EpiTest(testDataFile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
