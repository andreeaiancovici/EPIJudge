package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortAlmostSortedArray {

  public static List<Integer>
  sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
    List<Integer> result = new ArrayList<>();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k + 1);
    for(int i = 0; i <= k && sequence.hasNext(); i++) {
      minHeap.add(sequence.next());
    }
    while(!minHeap.isEmpty()) {
      Integer item = minHeap.poll();
      result.add(item);
      if(sequence.hasNext()) {
        minHeap.add(sequence.next());
      }
    }
    return result;
  }
  @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
  public static List<Integer>
  sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
    return sortApproximatelySortedData(sequence.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
