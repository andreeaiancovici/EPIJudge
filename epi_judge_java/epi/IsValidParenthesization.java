package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    Deque<Character> stack = new LinkedList<>();
    for (int i = 0; i < s.length(); i++) {
      char bracket = s.charAt(i);
      if ("{[(".indexOf(bracket) != -1) {
        stack.push(bracket);
      } else {
        if (!stack.isEmpty()) {
          if (bracket == '}' && stack.peek() != '{') {
            return false;
          } else if (bracket == ']' && stack.peek() != '[') {
            return false;
          } else if (bracket == ')' && stack.peek() != '(') {
            return false;
          } else {
            stack.pop();
          }
        } else return false;
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
