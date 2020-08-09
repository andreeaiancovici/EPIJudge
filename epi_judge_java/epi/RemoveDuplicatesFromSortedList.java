package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    ListNode<Integer> temp = L;
    ListNode<Integer> prev = null;
    while(temp != null) {
      if(prev != null) {
        while(temp != null && temp.data.equals(prev.data)) {
          prev.next = temp.next;
          temp = temp.next;
        }
      }
      if(temp != null) {
        prev = temp;
        temp = temp.next;
      }
    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
