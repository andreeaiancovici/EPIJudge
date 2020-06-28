package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {
    int start = 0, end = s.length() - 1;
    boolean isPalindrome = true;
    while(start <= end) {
      if(Character.isLetterOrDigit(s.charAt(start)) && Character.isLetterOrDigit(s.charAt(end))) {
        if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
          isPalindrome = false;
          break;
        } else {
          start++;
          end--;
        }
      }
      else if(Character.isLetterOrDigit(s.charAt(start))) {
        end--;
      } else if(Character.isLetterOrDigit(s.charAt(end))) {
        start++;
      } else {
        start++;
        end--;
      }
    }
    return isPalindrome;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
