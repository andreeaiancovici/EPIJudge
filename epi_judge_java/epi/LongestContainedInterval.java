package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class LongestContainedInterval {
  @EpiTest(testDataFile = "longest_contained_interval.tsv")

  public static int longestContainedRange(List<Integer> A) {
    Set<Integer> items = new HashSet<>(A);
    int longestCR = 0, currentCR = 0;
    for (Integer integer : A) {
      if (items.contains(integer)) {
        currentCR++;
        items.remove(integer);
        int upper = integer + 1;
        while (items.contains(upper)) {
          currentCR++;
          items.remove(upper);
          upper++;
        }
        int lower = integer - 1;
        while (items.contains(lower)) {
          currentCR++;
          items.remove(lower);
          lower--;
        }
        if (currentCR > longestCR) {
          longestCR = currentCR;
        }
        currentCR = 0;
      }
    }
    return longestCR;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestContainedInterval.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
