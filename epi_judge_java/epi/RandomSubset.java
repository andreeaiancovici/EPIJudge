package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class RandomSubset {
  // Returns a random k-sized subset of {0, 1, ..., n - 1}.
  public static List<Integer> randomSubset(int n, int k) {
    //O(k) space and time complexity
    Map<Integer, Integer> temp = new HashMap<>();
    Random r = new Random();
    for(int i = 0; i < k; i++) {
      int j = r.nextInt(n - i) + i;
      Integer t1 = temp.get(j);//null,0,1
      Integer t2 = temp.get(i);//null,null,null
      if(t1 == null &&  t2 == null) { //(0,28),(28,0)
        temp.put(j, i);
        temp.put(i, j);
      }
      else if(t1 == null) {
        temp.put(j, t2);
        temp.put(i, j);
      } else if(t2 == null) {
        temp.put(j, i);//(0,28),(28,1),(1,0)/(0,28),(28,2),(1,0),(2,1)
        temp.put(i, t1);
      } else {
        temp.put(i, t1);
        temp.put(j, t2);
      }
    }

    List<Integer> result = new ArrayList<>(k);
    for(int i = 0; i < k; i++) {
      result.add(temp.get(i));
    }
    return result;

    //O(n) space and time complexity
//    List<Integer> result = new ArrayList<>();
//    for(int i = 0; i < n; i++) {
//      result.add(i);
//    }
//
//    Random r = new Random();
//    for(int i = 0; i < k; i++) {
//      int j = r.nextInt(n - i) + i;
//      Collections.swap(result, i, j);
//    }
//
//    return result.subList(0, k);
  }
  private static boolean randomSubsetRunner(TimedExecutor executor, int n,
                                            int k) throws Exception {
    List<List<Integer>> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 1000000; ++i) {
        results.add(randomSubset(n, k));
      }
    });

    int totalPossibleOutcomes = RandomSequenceChecker.binomialCoefficient(n, k);
    List<Integer> A = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      A.add(i);
    }
    List<List<Integer>> combinations = new ArrayList<>();
    for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(n, k); ++i) {
      combinations.add(RandomSequenceChecker.computeCombinationIdx(A, n, k, i));
    }
    List<Integer> sequence = new ArrayList<>();
    for (List<Integer> result : results) {
      Collections.sort(result);
      sequence.add(combinations.indexOf(result));
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, totalPossibleOutcomes, 0.01);
  }

  @EpiTest(testDataFile = "random_subset.tsv")
  public static void randomSubsetWrapper(TimedExecutor executor, int n, int k)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> randomSubsetRunner(executor, n, k));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RandomSubset.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
