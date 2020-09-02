package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    int start = 1, end = k, m;
    double sqrM;
    while (start <= end) {
      m = ((end - start) / 2) + start;
      sqrM = Math.pow(m, 2);
      if (sqrM > k) {
        end = m - 1;
      } else if (sqrM == k) {
        return m;
      } else {
        start = m + 1;
      }
    }
    return start - 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
