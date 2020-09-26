package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IsStringPermutableToPalindrome {
  @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

  public static boolean canFormPalindrome(String s) {
    Map<Character, Integer> letters = new HashMap<>();
    char[] chars = s.toCharArray();
    for (char c : chars) {
      if (letters.containsKey(c)) {
        letters.put(c, letters.get(c) + 1);
      } else {
        letters.put(c, 1);
      }
    }
    int oddCount = 0;
    for (int value : letters.values()) {
      if (value % 2 != 0) {
        if (oddCount >= 1) {
          return false;
        }
        oddCount++;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
