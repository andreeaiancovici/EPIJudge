package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1, ListNode<Integer> L2) {
    if (L1 == null && L2 == null) {
      return null;
    }
    ListNode<Integer> L3 = new ListNode<>(0, L1);
    ListNode<Integer> temp = L3;
    while (L1 != null || L2 != null) {
      if(L1 != null && L2 != null) {
        if (L1.data < L2.data) {
          temp.next = L1;
          temp = temp.next;
          L1 = L1.next;
        } else {
          temp.next = L2;
          temp = temp.next;
          L2 = L2.next;
        }
      } else if(L1 != null) {
        temp.next = L1;
        temp = temp.next;
        L1 = L1.next;
      } else {
        temp.next = L2;
        temp = temp.next;
        L2 = L2.next;
      }
    }
    return L3.next;
  }

  private static void mergeTwoSortedListsInternal(ListNode<Integer> L1, ListNode<Integer> L2, ListNode<Integer> temp) {

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
