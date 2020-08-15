package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import java.util.List;
public class CircularQueue {

  public static class Queue {
    private int[] queue;
    private int first;
    private int last;

    public Queue(int capacity) {
      queue = new int[capacity];
      first = 0;
      last = 0;
    }
    public void enqueue(Integer x) {
      if (last >= queue.length) {
        resize();
      }
      queue[last++] = x;
    }
    public Integer dequeue() {
      if (first <= last) {
        int value = queue[first];
        queue[first] = 0;
        if (first < last) {
          first++;
        }
        if (first == last) {
          first = 0;
          last = 0;
        }
        return value;
      } else return null;
    }
    public int size() {
      return last - first;
    }
    @Override
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      for(int i = 0; i < queue.length; i++) {
        stringBuilder.append(queue[i]);
        if(i != queue.length - 1) {
          stringBuilder.append(", ");
        }
      }
      return stringBuilder.toString();
    }

    private void resize() {
      int[] temp = queue.clone();
      queue = new int[2 * queue.length];
      System.arraycopy(temp, first, queue, 0, temp.length - first);
      last-=first;
      first = 0;
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTest(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
