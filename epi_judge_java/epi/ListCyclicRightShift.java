package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
    if (L == null) {
      return null;
    }
    if (k == 0) {
      return L;
    }
    ListNode<Integer> dummy = new ListNode<>(0, L);
    ListNode<Integer> first = L;
    ListNode<Integer> prevFirst = null;
    ListNode<Integer> prevSecond = null;
    int n = 0;
    while (first != null) {
      n++;
      first = first.next;
    }
    int diff = k % n;
    if (diff == 0) {
      return L;
    }
    diff--;
    first = L;
    while (diff > 0) {
      first = first.next;
      diff--;
    }
    ListNode<Integer> second = L;
    first = first.next;
    while (first != null) {
      prevSecond = second;
      prevFirst = first;
      second = second.next;
      first = first.next;
    }
    dummy.next = second;
    if (prevFirst != null) {
      prevFirst.next = L;
    }
    if (prevSecond != null) {
      prevSecond.next = null;
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
