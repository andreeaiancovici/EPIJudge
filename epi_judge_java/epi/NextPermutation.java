package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    if(perm.size() == 0) {
      return new ArrayList<>();
    }

    int i = perm.size() - 1;
    while(i > 0 && perm.get(i) <= perm.get(i - 1)) {
      i--;
    }

    if(i == 0) {
      return new ArrayList<>();
    }

    int val = perm.get(i - 1);
    int j = perm.size() - 1;
    while(j > i - 1 && perm.get(j) <= val) {
      j--;
    }

    Collections.swap(perm, i - 1, j);
    Collections.reverse(perm.subList(i, perm.size()));
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
