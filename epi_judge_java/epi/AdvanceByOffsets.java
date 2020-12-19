package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    List<Integer> memo = new ArrayList<>(Collections.nCopies(maxAdvanceSteps.size(), 0));
    memo.set(memo.size() - 1, 1);
    for(int i = maxAdvanceSteps.size() - 2; i >= 0; i--) {
      int jump = Math.min(maxAdvanceSteps.size() - 1, i + maxAdvanceSteps.get(i));
      for(int j = i + 1; j <= jump; j++) {
        if(memo.get(j) == 1) {
          memo.set(i, 1);
          break;
        }
      }
    }
    return memo.get(0) == 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
