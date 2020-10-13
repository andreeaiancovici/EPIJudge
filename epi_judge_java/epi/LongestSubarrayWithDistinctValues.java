package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class LongestSubarrayWithDistinctValues {
  @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")

  public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
    Map<Integer, Integer> lastOccurrences = new HashMap<>();
    int currentSubarray = 0, longestSubarray = 0, startSubarray = 0;
    for(int i = 0; i < A.size(); i++) {
      if(lastOccurrences.containsKey(A.get(i)) && lastOccurrences.get(A.get(i)) >= startSubarray) {
        int lastOccurrence = lastOccurrences.get(A.get(i));
        currentSubarray = i - lastOccurrence;
        startSubarray = lastOccurrence + 1;
      } else {
        currentSubarray++;
      }
      lastOccurrences.put(A.get(i), i);
      if(currentSubarray > longestSubarray) {
        longestSubarray = currentSubarray;
      }
    }
    return longestSubarray;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
