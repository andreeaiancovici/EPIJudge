package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    ListNode<Integer> oneStep = L;
    ListNode<Integer> twoStep = L;
    while(oneStep != null && twoStep != null && twoStep.next != null) {
      oneStep = oneStep.next;
      twoStep = twoStep.next.next;
    }
    ListNode<Integer> firstHalf = L;
    ListNode<Integer> secondHalf = reverse(oneStep);
    while(firstHalf != null && secondHalf != null) {
      if(!firstHalf.data.equals(secondHalf.data)) {
        return false;
      }
      firstHalf = firstHalf.next;
      secondHalf = secondHalf.next;
    }
    return true;
  }

  private static ListNode<Integer> reverse(ListNode<Integer> list) {
    ListNode<Integer> prev = null;
    while(list != null) {
      if(prev != null) {
        ListNode<Integer> temp = list.next;
        list.next = prev;
        prev = list;
        list = temp;
      } else {
        prev = list;
        list = list.next;
        prev.next = null;
      }
    }
    return prev;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
