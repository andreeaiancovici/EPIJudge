package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SmallestSubarrayCoveringSet {

  // Represent subarray by starting and ending indices, inclusive.
  private static class Subarray {
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph, Set<String> keywords) {
      Map<String, Integer> keywordsCount = new HashMap<>();
      keywords.forEach(keyword -> keywordsCount.put(keyword, 0));
      Subarray subarray = new Subarray(-1, -1);
      int start = 0, end = 0, keywordCount = 0;
      while (start < paragraph.size() && end < paragraph.size()) {
          if (keywordCount == keywordsCount.size()) {
              if ((subarray.end == -1 && subarray.start == -1) || end - start < subarray.end - subarray.start) {
                  subarray.start = start;
                  subarray.end = end;
              }
              String word = paragraph.get(start);
              if (keywordsCount.containsKey(word)) {
                  int count = keywordsCount.get(word);
                  if (count == 1) {
                      keywordCount--;
                  }
                  keywordsCount.put(word, count - 1);
              }
              start++;
              if(keywordCount < keywordsCount.size()) {
                  end++;
              }
          } else {
              String word = paragraph.get(end);
              if (keywordsCount.containsKey(word)) {
                  int count = keywordsCount.get(word);
                  if (count == 0) {
                      keywordsCount.put(word, 1);
                      keywordCount++;
                  } else {
                      keywordsCount.put(word, count + 1);
                  }
              }
              if(keywordCount < keywordsCount.size()) {
                  end++;
              }
          }
      }
      return subarray;
  }
  @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
  public static int findSmallestSubarrayCoveringSetWrapper(
      TimedExecutor executor, List<String> paragraph, Set<String> keywords)
      throws Exception {
    Set<String> copy = new HashSet<>(keywords);

    Subarray result = executor.run(
        () -> findSmallestSubarrayCoveringSet(paragraph, keywords));

    if (result.start < 0 || result.start >= paragraph.size() ||
        result.end < 0 || result.end >= paragraph.size() ||
        result.start > result.end)
      throw new TestFailure("Index out of range");

    for (int i = result.start; i <= result.end; i++) {
      copy.remove(paragraph.get(i));
    }

    if (!copy.isEmpty()) {
      throw new TestFailure("Not all keywords are in the range");
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringSet.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
