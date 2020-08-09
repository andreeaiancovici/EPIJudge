package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SubstringMatch {
  @EpiTest(testDataFile = "substring_match.tsv")

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int rabinKarp(String t, String s) {
    if (s.length() > t.length()) {
      return -1;
    }
    if (s.trim().isEmpty()) {
      return 0;
    }

    int sHash = 0;
    int tHash = 0;
    int MOD = 31;

    for (int i = 0; i < s.length() && i < t.length(); i++) {
      sHash += s.charAt(i);
      tHash += t.charAt(i);
    }
    sHash %= MOD;
    int quotient = tHash / MOD;
    tHash %= MOD;

    for (int i = 0; i < t.length(); i++) {
      if (sHash == tHash && areEqual(i, t, s)) {
        return i;
      } else {
        if (t.length() - i > s.length()) {
          tHash = quotient * MOD + tHash;
          tHash -= t.charAt(i);
          tHash += t.charAt(i + s.length());
          quotient = tHash / MOD;
          tHash %= MOD;
        } else break;
      }
    }
    return -1;
  }

  private static boolean areEqual(int i, String t, String s) {
    boolean found = true;
    int j = i, k = 0;
    while (j < t.length() && k < s.length()) {
      if (t.charAt(j) != s.charAt(k)) {
        found = false;
      }
      j++;
      k++;
    }
    if (k < s.length()) {
      found = false;
    }
    return found;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatch.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
