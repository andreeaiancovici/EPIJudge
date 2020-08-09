package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    if(L == null || start > finish) {
      return L;
    }
    ListNode<Integer> temp = L;
    ListNode<Integer> prev = null;
    ListNode<Integer> tail = null;
    ListNode<Integer> head = null;
    ListNode<Integer> prevTail = null;
    int count = 1;
    while(temp != null) {
      ListNode<Integer> dummy = temp.next;
      if(count == start) {
        tail = temp;
        prevTail = prev;
      }
      if(start < count && count < finish) {
        temp.next = prev;
      }
      if(count == finish) {
        if(prevTail != null) {
          prevTail.next = temp;
        }
        head = temp;
        head.next = prev;
        if(tail != null) {
          tail.next = dummy;
        }
        break;
      }
      prev = temp;
      temp = dummy;
      count++;
    }
    if(start == 1) {
      return head;
    } else {
      return L;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
