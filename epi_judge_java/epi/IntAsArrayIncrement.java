package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    boolean carry = true;
    for (int i = A.size() - 1; i >= 0 && carry; i--) {
      if (A.get(i) < 9) {
        A.set(i, A.get(i) + 1);
        carry = false;
      } else {
        A.set(i, 0);
      }
    }
    if (carry) {
      A.add(0, 1);
    }
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
