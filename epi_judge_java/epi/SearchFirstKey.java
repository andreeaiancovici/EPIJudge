package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    int start = 0, end = A.size() - 1, m;
    while (start < end) {
      m = ((end - start) / 2) + start;
      if (k <= A.get(m)) {
        end = m;
      } else {
        start = m + 1;
      }
    }
    if (start == end) {
      return A.get(start) == k ? start : -1;
    } else return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
