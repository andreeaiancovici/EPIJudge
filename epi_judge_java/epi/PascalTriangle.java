package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    @EpiTest(testDataFile = "pascal_triangle.tsv")

    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();

        List<Integer> row;

        if (numRows >= 1) {
            row = new ArrayList<>();
            row.add(1);
            pascalTriangle.add(row);
        }

        if (numRows >= 2) {
            row = new ArrayList<>();
            row.add(1);
            row.add(1);
            pascalTriangle.add(row);
        }

        if (numRows >= 3) {
            for(int r = 3; r <= numRows; r++) {
              row = new ArrayList<>();
              row.add(1);
              for (int i = 1; i < r - 1; i++) {
                int value = 0;
                for (List<Integer> tempRow : pascalTriangle) {
                  if(tempRow.size() >= i) {
                    value += tempRow.get(i - 1);
                  }
                }
                row.add(value);
              }
              row.add(1);
              pascalTriangle.add(row);
            }
        }

        return pascalTriangle;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
