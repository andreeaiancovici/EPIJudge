package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    if(input.length > 1) {
      reverse(input, 0, input.length);
      int i = 0;
      int start = 0;
      while(i <= input.length) {
        if(i == input.length || Character.isWhitespace(input[i])) {
          reverse(input, start, i);
          start = i + 1;
        }
        i++;
      }
    }
  }

  private static void reverse(char[] input, int start, int end) {
    char temp;
    int middle = start + ((end - start) / 2);
    for(int i = start, diff = 0; i < middle; i++, diff++) {
      temp = input[i];
      input[i] = input[end - 1 - diff];
      input[end - 1 - diff] = temp;
    }
  }

  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
