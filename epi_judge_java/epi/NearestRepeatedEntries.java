package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    Map<String, Integer> distances = new HashMap<>();
    int closestDistance = Integer.MAX_VALUE;
    for (int i = 0; i < paragraph.size(); i++) {
      String word = paragraph.get(i);
      if (distances.containsKey(word)) {
        int distance = distances.get(word);
        closestDistance = Math.min(i - distance, closestDistance);
      }
      distances.put(word, i);
    }
    return closestDistance == Integer.MAX_VALUE ? -1 : closestDistance;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
