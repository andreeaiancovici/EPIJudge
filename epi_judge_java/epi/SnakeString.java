package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SnakeString {
  @EpiTest(testDataFile = "snake_string.tsv")

  public static String snakeString(String s) {
    StringBuilder sb = new StringBuilder();
    snakeString(s, 1, 4, sb);
    snakeString(s, 0, 2, sb);
    snakeString(s, 3, 4, sb);
    return sb.toString();
  }

  private static void snakeString(String s, int index, int step, StringBuilder sb) {
    for (int i = index; i < s.length(); i += step) {
      sb.append(s.charAt(i));
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SnakeString.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
