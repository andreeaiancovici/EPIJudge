package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    int result = 0;
    if (expression == null || expression.trim().isEmpty()) {
      return result;
    }
    Deque<Integer> operands = new LinkedList<>();

    String[] items = expression.split(",");
    for (String item : items) {
      try {
        int number = Integer.parseInt(item);
        operands.push(number);
      } catch (NumberFormatException e) {
        int firstOperand = operands.pop();
        int secondOperand = operands.pop();
        switch (item) {
          case "+":
            operands.push(secondOperand + firstOperand);
            break;
          case "-":
            operands.push(secondOperand - firstOperand);
            break;
          case "*":
            operands.push(secondOperand * firstOperand);
            break;
          case "/":
            operands.push(secondOperand / firstOperand);
            break;
        }
      }
    }
    return operands.pop();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
