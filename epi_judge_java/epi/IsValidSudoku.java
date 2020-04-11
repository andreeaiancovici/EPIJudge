package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IsValidSudoku {
    @EpiTest(testDataFile = "is_valid_sudoku.tsv")

    // Check if a partially filled matrix has any conflicts.
    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
        //O(n2)
        for (int i = 0; i < partialAssignment.size(); i++) {
            if (!isValid(partialAssignment, i, i + 1, 0, partialAssignment.size())) {
                return false;
            }
        }

        //O(n2)
        for (int j = 0; j < partialAssignment.size(); j++) {
            if (!isValid(partialAssignment, 0, partialAssignment.size(), j, j + 1)) {
                return false;
            }
        }

        int sectionSize = (int) Math.sqrt(partialAssignment.size());
        for (int i = 0; i < partialAssignment.size(); i++) {
            for (int j = 0; j < partialAssignment.size(); j++) {
                if ((i + 1) % sectionSize == 0 && (j + 1) % sectionSize == 0) {
                    if (!isValid(partialAssignment, i - 2, i + 1, j - 2, j + 1)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean isValid(List<List<Integer>> partialAssignment, int rowStart, int rowEnd, int colStart, int colEnd) {
        List<Boolean> isPresent = new ArrayList<>(Collections.nCopies(partialAssignment.size() + 1, false));
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                int value = partialAssignment.get(i).get(j);
                if (isPresent.get(value)) {
                    return false;
                }
                if(value != 0) {
                  isPresent.set(value, true);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
