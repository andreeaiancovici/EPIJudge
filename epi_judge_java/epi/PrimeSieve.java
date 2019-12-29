package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    /** O(n*log(log n)) **/
        List<Integer> primes = new ArrayList<>();

        if(n < 2) {
          return primes;
        }

        boolean[] isPrime = new boolean[n + 1];

        for(int i = 0; i < isPrime.length; i++) {
          isPrime[i] = true;
        }

        isPrime[0] = false;
        isPrime[1] = false;

        primes.add(2);

        for(int i = 2; i < isPrime.length; i++) {
          if(i % 2 == 0) {
            isPrime[i] = false;
          }
        }

        for(int i = 3; i < isPrime.length; i++) {
          if(isPrime[i]) {
            primes.add(i);
            for(int j = i; j < isPrime.length; j+=i) {
              isPrime[j] = false;
            }
          }
        }

        return primes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
