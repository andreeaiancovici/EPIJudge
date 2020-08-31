package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  public static int searchSmallest(List<Integer> A) {
    int start = 0, end = A.size() - 1, m;
    while(start <= end) {
      m = ((end - start) / 2) + start;
      if (A.get(m) > A.get(A.size() - 1)) {
        start = m + 1;
      } else if (A.get(m).equals(A.get(A.size() - 1))) {
        return m;
      } else {
        end = m - 1;
      }
    }
    return start;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
