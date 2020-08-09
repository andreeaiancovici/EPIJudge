package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;


public class StackWithMax {

  private static class Element {
     private int value;
     private int count;

     Element(int value, int count) {
       this.value = value;
       this.count = count;
     }

     void setCount(int count) {
       this.count = count;
     }
  }

  public static class Stack {
    private Deque<Integer> stack = new LinkedList<>();
    private Deque<Element> max = new LinkedList<>();
    public boolean empty() {
      return stack.isEmpty();
    }

    public Integer max() {
      if (max.isEmpty()) {
        return null;
      }
      return max.peek().value;
    }

    public Integer pop() {
      if (stack.isEmpty()) {
        return null;
      }
      if(!max.isEmpty()) {
        if (stack.peek() == max.peek().value) {
          if (max.peek().count > 1) {
            int count = max.peek().count;
            max.peek().setCount(count - 1);
          } else {
            max.pop();
          }
        }
      }
      return stack.pop();
    }

    public void push(Integer x) {
      stack.push(x);
      if (max.isEmpty()) {
        max.push(new Element(x, 1));
      } else {
        if (x > max.peek().value) {
          max.push(new Element(x, 1));
        } else if (x == max.peek().value) {
          int count = max.peek().count;
          max.peek().setCount(count + 1);
        }
      }
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTest(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
