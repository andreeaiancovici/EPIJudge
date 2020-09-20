package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> A) {
    int start = 0, end = A.size() - 1, pivot;
    while (start <= end) {
      Random random = new Random();
      pivot = random.nextInt(end - start + 1) + start;
      int pivotValue = A.get(pivot);
      Collections.swap(A, end, pivot);
      int newPivot = start;
      int i = start;
      while (i <= end) {
        if(A.get(i) > pivotValue) {
          Collections.swap(A, i, newPivot);
          newPivot++;
        }
        i++;
      }
      Collections.swap(A, end, newPivot);
      if (newPivot == k - 1) {
        return A.get(newPivot);
      } else if (newPivot > k - 1) {
        end = newPivot - 1;
      } else {
        start = newPivot + 1;
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
