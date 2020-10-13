package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SmallestSubarrayCoveringAllValues {

  public static class Subarray {
    // Represent subarray by starting and ending indices, inclusive.
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray
  findSmallestSequentiallyCoveringSubset(List<String> paragraph,
                                         List<String> keywords) {
    Map<String, Integer> keywordToIndex = new HashMap<>();
    for (int i = 0; i < keywords.size(); i++) {
      keywordToIndex.put(keywords.get(i), i);
    }
    Map<Integer, Integer> latestOccurrence = new HashMap<>();
    Map<Integer, Integer> indexToDistance = new HashMap<>();
    int index = 0;
    Subarray subarray = new Subarray(-1, -1);
    while (index < paragraph.size()) {
      String word = paragraph.get(index);
      if (keywordToIndex.containsKey(word)) {
        int keywordIndex = keywordToIndex.get(word);
        if (keywordIndex == 0) {
          indexToDistance.put(0, 0);
          latestOccurrence.put(keywordIndex, index);
        } else if (indexToDistance.containsKey(keywordIndex - 1)) {
          int distanceToPrevKeyword = index - latestOccurrence.get(keywordIndex - 1);
          indexToDistance.put(keywordIndex, distanceToPrevKeyword + indexToDistance.get(keywordIndex - 1));
          latestOccurrence.put(keywordIndex, index);
        }
        if (keywordIndex == keywords.size() - 1) {
          if ((subarray.start == -1 && subarray.end == -1) || subarray.end - subarray.start > indexToDistance.get(keywordIndex)) {
            if (indexToDistance.containsKey(keywordIndex)) {
              subarray.start = index - indexToDistance.get(keywordIndex);
              subarray.end = index;
            }
          }
        }
      }
      index++;
    }
    return subarray;
  }
  @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
  public static int findSmallestSequentiallyCoveringSubsetWrapper(
      TimedExecutor executor, List<String> paragraph, List<String> keywords)
      throws Exception {
    Subarray result = executor.run(
        () -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

    int kwIdx = 0;
    if (result.start < 0) {
      throw new TestFailure("Subarray start index is negative");
    }
    int paraIdx = result.start;

    while (kwIdx < keywords.size()) {
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Not all keywords are in the generated subarray");
      }
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Subarray end index exceeds array size");
      }
      if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
        kwIdx++;
      }
      paraIdx++;
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
