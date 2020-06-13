package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SpreadsheetEncoding {
    @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

    public static int ssDecodeColID(final String col) {
        int id = 0;
        for (int i = col.length() - 1, j = 0; i >= 0; i--, j++) {
          id += (getNumber(col.charAt(i)) * Math.pow(26, j));
        }
        return id;
    }

    private static int getNumber(char letter) {
        switch (letter) {
            case 'A':
                return 1;
            case 'B':
                return 2;
            case 'C':
                return 3;
            case 'D':
                return 4;
            case 'E':
                return 5;
            case 'F':
                return 6;
            case 'G':
                return 7;
            case 'H':
                return 8;
            case 'I':
                return 9;
            case 'J':
                return 10;
            case 'K':
                return 11;
            case 'L':
                return 12;
            case 'M':
                return 13;
            case 'N':
                return 14;
            case 'O':
                return 15;
            case 'P':
                return 16;
            case 'Q':
                return 17;
            case 'R':
                return 18;
            case 'S':
                return 19;
            case 'T':
                return 20;
            case 'U':
                return 21;
            case 'V':
                return 22;
            case 'W':
                return 23;
            case 'X':
                return 24;
            case 'Y':
                return 25;
            case 'Z':
                return 26;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
