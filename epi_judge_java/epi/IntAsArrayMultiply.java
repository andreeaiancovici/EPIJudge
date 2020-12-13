package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
    int product, carry = 0;

    boolean isNegative = ((num1.get(0) < 0 && num2.get(0) > 0) || (num2.get(0) < 0 && num1.get(0) > 0));

    if(num1.get(0) < 0) {
      num1.set(0, num1.get(0) * -1);
    }

    if(num2.get(0) < 0) {
      num2.set(0, num2.get(0) * -1);
    }

    for (int i = num1.size() - 1; i >= 0; i--) {
      for (int j = num2.size() - 1; j >= 0; j--) {
        product = num1.get(i) * num2.get(j) + carry;
        carry = product / 10;
        result.set(i + j + 1, result.get(i + j + 1) + product % 10);
        carry += result.get(i + j + 1) / 10;
        result.set(i + j + 1, result.get(i + j + 1) % 10);
      }
      if (carry > 0) {
        result.set(i, carry);
        carry = 0;
      }
    }

    while(result.size() > 1 && result.get(0) == 0) {
      result.remove(0);
    }

    if(isNegative) {
      result.set(0, result.get(0) * -1);
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}