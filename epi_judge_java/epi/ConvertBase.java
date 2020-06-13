package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
    @EpiTest(testDataFile = "convert_base.tsv")

    public static String convertBase(String numAsString, int b1, int b2) {
        int numInBaseTen = 0;
        boolean isNegative = false;
        for (int i = numAsString.length() - 1, j = 0; i >= 0; i--, j++) {
            if (i == 0 && numAsString.charAt(i) == '-') {
                isNegative = true;
            } else {
                char c = numAsString.charAt(i);
                numInBaseTen += (((c - '0') > 9 ? getValue(c) : c - '0') * Math.pow(b1, j));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if(numInBaseTen == 0) {
          stringBuilder.append("0");
        }
        while (numInBaseTen != 0) {
            int number = numInBaseTen % b2;
            stringBuilder.append(number > 9 ? getLetter(number) : number);
            numInBaseTen /= b2;
        }
        if (isNegative) {
            stringBuilder.append("-");
        }
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    private static String getLetter(int number) {
        switch (number) {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
        }
        return "";
    }

    private static int getValue(char letter) {
        switch (letter) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
