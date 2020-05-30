package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrderingSegments {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    List<Integer> result = new ArrayList<>();
    matrixInSpiralOrderRecursive(result, squareMatrix, squareMatrix.size(), 0);
    return result;
  }

  private static void matrixInSpiralOrderRecursive(List<Integer> result, List<List<Integer>> squareMatrix, int n, int margin) {
    if(n == 0) {
      return;
    }
    if(n % 2 == 1 && margin == n/2) {
      result.add(squareMatrix.get(margin).get(margin));
      return;
    }
    if(n % 2 == 0 && margin == (n/2 - 1)) {
      result.add(squareMatrix.get(margin).get(margin));
      result.add(squareMatrix.get(margin).get(margin + 1));
      result.add(squareMatrix.get(margin + 1).get(margin + 1));
      result.add(squareMatrix.get(margin + 1).get(margin));
      return;
    }

    for(int j = margin; j < n - 1 - margin; j++) {
      result.add(squareMatrix.get(margin).get(j));
    }
    for(int i = margin; i < n - 1 - margin; i++) {
      result.add(squareMatrix.get(i).get(n - 1 - margin));
    }
    for(int j = n - 1 - margin; j > margin; j--) {
      result.add(squareMatrix.get(n - 1 - margin).get(j));
    }
    for(int i = n - 1 - margin; i > margin; i--) {
      result.add(squareMatrix.get(i).get(margin));
    }

    matrixInSpiralOrderRecursive(result, squareMatrix, n, margin + 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
