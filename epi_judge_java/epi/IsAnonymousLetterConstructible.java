package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                          String magazineText) {
    Map<Character, Integer> charFrequencies = new HashMap<>();
    for (int i = 0; i < letterText.length(); i++) {
      char c = letterText.charAt(i);
      if (charFrequencies.containsKey(c)) {
        charFrequencies.put(c, charFrequencies.get(c) + 1);
      } else {
        charFrequencies.put(c, 1);
      }
    }
    for (int i = 0; i < magazineText.length(); i++) {
      char c = magazineText.charAt(i);
      if (c != ' ') {
        if (charFrequencies.containsKey(c)) {
          int count = charFrequencies.get(c);
          if (count > 0) {
            if (count - 1 == 0) {
              charFrequencies.remove(c);
            } else {
              charFrequencies.put(c, count - 1);
            }
          }
        }
      }
      if(charFrequencies.isEmpty()) {
        return true;
      }
    }
    return charFrequencies.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
