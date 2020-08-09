package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    ListNode<Integer> temp = L, prevOdd = null, prevEven = null, firstOdd = null;
    int count = 0;
    while (temp != null) {
      if (count % 2 == 0) {
        if (prevEven == null) {
          prevEven = temp;
        } else {
          prevEven.next = temp;
          prevEven = prevEven.next;
        }
      } else {
        if (prevOdd == null) {
          firstOdd = temp;
          prevOdd = temp;
        } else {
          prevOdd.next = temp;
          prevOdd = prevOdd.next;
        }
      }
      count++;
      temp = temp.next;
    }
    if (prevEven != null) {
      prevEven.next = firstOdd;
    }
    if (prevOdd != null) {
      prevOdd.next = null;
    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
