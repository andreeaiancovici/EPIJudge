package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.concurrent.atomic.*;

public class SortedArraysMerge {

  static class ArrayEntry {

    int value;
    int id;

    ArrayEntry(int value, int id) {
      this.value = value;
      this.id = id;
    }
  }

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Integer> result = new ArrayList<>();
    PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(), Comparator.comparingInt(o -> o.value));

    List<Iterator> iterators = new ArrayList<>(sortedArrays.size());
    for(int i = 0; i < sortedArrays.size(); i++) {
      Iterator<Integer> iterator = sortedArrays.get(i).iterator();
      if(iterator.hasNext()) {
        minHeap.add(new ArrayEntry(iterator.next(), i));
      }
      iterators.add(iterator);
    }

    while(!minHeap.isEmpty()) {
      ArrayEntry arrayEntry = minHeap.poll();
      result.add(arrayEntry.value);
      if(iterators.get(arrayEntry.id).hasNext()) {
        minHeap.add(new ArrayEntry((Integer) iterators.get(arrayEntry.id).next(), arrayEntry.id));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
