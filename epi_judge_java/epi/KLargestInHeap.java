package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
public class KLargestInHeap {

  static class HeapEntry {
    int value;
    int index;

    HeapEntry(int value, int index) {
      this.value = value;
      this.index = index;
    }
  }

  @EpiTest(testDataFile = "k_largest_in_heap.tsv")

  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
    List<Integer> result = new ArrayList<>(k);
    PriorityQueue<HeapEntry> maxHeap = new PriorityQueue<>(3, (o1, o2) -> Integer.compare(o2.value, o1.value));
    maxHeap.add(new HeapEntry(A.get(0), 0));
    while(!maxHeap.isEmpty() && result.size() < k) {
        HeapEntry maxCurrent = maxHeap.poll();
        if(maxCurrent != null) {
          result.add(maxCurrent.value);
          if(A.size() > 2 * maxCurrent.index + 1) {
            maxHeap.add(new HeapEntry(A.get(2 * maxCurrent.index + 1), 2 * maxCurrent.index + 1));
          }
          if(A.size() > 2 * maxCurrent.index + 2) {
            maxHeap.add(new HeapEntry(A.get(2 * maxCurrent.index + 2), 2 * maxCurrent.index + 2));
          }
        }
    }
    return result;
  }
  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
