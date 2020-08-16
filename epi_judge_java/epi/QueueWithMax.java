package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.*;

public class QueueWithMax {

  static class Element {
    int value;
    int count;

    Element(int value, int count) {
      this.value = value;
      this.count = count;
    }
  }

  private Deque<Integer> queue = new LinkedList<>();
  private Deque<Element> queueWithMax = new LinkedList<>();

  public void enqueue(Integer x) {
    queue.add(x);
    Element element = queueWithMax.peekLast();
    if (element != null) {
      if (x > element.value) {
        while (element != null && x > element.value) {
          queueWithMax.pollLast();
          element = queueWithMax.peekLast();
        }
        queueWithMax.addLast(new Element(x, 1));
      } else if (x == element.value) {
        element.count++;
      } else {
        queueWithMax.addLast(new Element(x, 1));
      }
    } else {
      queueWithMax.addLast(new Element(x, 1));
    }
  }
  public Integer dequeue() {
    Integer value = queue.pollFirst();
    if(value != null) {
      Element element = queueWithMax.peekFirst();
      if(value == element.value) {
        if (element.count == 1) {
          queueWithMax.pollFirst();
        } else if (element.count > 1) {
          element.count--;
        }
      }
      return value;
    } else return null;
  }
  public Integer max() {
    Element element = queueWithMax.peekFirst();
    if(element != null) {
      return element.value;
    } else return null;
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "queue_with_max.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    try {
      QueueWithMax q = new QueueWithMax();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new QueueWithMax();
          break;
        case "enqueue":
          q.enqueue(op.arg);
          break;
        case "dequeue":
          int result = q.dequeue();
          if (result != op.arg) {
            throw new TestFailure("Dequeue: expected " +
                                  String.valueOf(op.arg) + ", got " +
                                  String.valueOf(result));
          }
          break;
        case "max":
          int s = q.max();
          if (s != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "QueueWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
