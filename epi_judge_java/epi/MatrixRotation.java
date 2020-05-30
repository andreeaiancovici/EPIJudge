package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixRotation {

    public static void rotateMatrix(List<List<Integer>> squareMatrix) {
        rotateMatrixRecursive(squareMatrix, squareMatrix.size() - 1, 0);
    }

    private static void rotateMatrixRecursive(List<List<Integer>> squareMatrix, int n, int margin) {
        if (margin == (n + 1) / 2) {
            return;
        }
        int t1, t2, t3;
        for (int j = margin; j < n - margin; j++) {
          t1 = squareMatrix.get(j).get(n - margin);
          t2 = squareMatrix.get(n - margin).get(n - j);
          t3 = squareMatrix.get(n - j).get(margin);

          squareMatrix.get(j).set(n - margin, squareMatrix.get(margin).get(j));
          squareMatrix.get(n - margin).set(n - j, t1);
          squareMatrix.get(n - j).set(margin, t2);
          squareMatrix.get(margin).set(j, t3);
        }
        rotateMatrixRecursive(squareMatrix, n, margin + 1);
    }

    @EpiTest(testDataFile = "matrix_rotation.tsv")
    public static List<List<Integer>>
    rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
        rotateMatrix(squareMatrix);
        return squareMatrix;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
