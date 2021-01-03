package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    if (perm.size() >= 2) {
      if (perm.get(perm.size() - 1) > perm.get(perm.size() - 2)) {
        Collections.swap(perm, perm.size() - 1, perm.size() - 2);
      } else {
        int i = perm.size() - 1;
        while(i > 0 && perm.get(i) <= perm.get(i - 1)) {
          i--;
        }
        if(i - 1 >= 0) {
          int j = perm.size() - 1;
          while (perm.get(j) <= perm.get(i - 1)) {
            j--;
          }
          Collections.swap(perm, j, i - 1);
          Collections.reverse(perm.subList(i, perm.size()));
        } else {
          return Collections.emptyList();
        }
      }
    } else {
      return Collections.emptyList();
    }
    return perm;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
