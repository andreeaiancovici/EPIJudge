package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class RomanToInteger {


    private static Map<String, Integer> romanToDecimal = new HashMap<>();

    static {
        romanToDecimal.put("I", 1);
        romanToDecimal.put("IV", 4);
        romanToDecimal.put("V", 5);
        romanToDecimal.put("IX", 9);
        romanToDecimal.put("X", 10);
        romanToDecimal.put("XL", 40);
        romanToDecimal.put("L", 50);
        romanToDecimal.put("XC", 90);
        romanToDecimal.put("C", 100);
        romanToDecimal.put("CD", 400);
        romanToDecimal.put("D", 500);
        romanToDecimal.put("CM", 900);
        romanToDecimal.put("M", 1000);
    }

    @EpiTest(testDataFile = "roman_to_integer.tsv")
    public static int romanToInteger(String s) {
        int result = 0;
        char prev = ' ';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer value = null;
            if ((prev == 'I' || prev == 'X' || prev == 'C')) {
                value = romanToDecimal.get(String.valueOf(prev) + c);
            }
            if (value != null) {
                result -= romanToDecimal.get(String.valueOf(prev));
                result += value;
            } else {
                result += romanToDecimal.get(String.valueOf(c));
            }
            prev = c;
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
