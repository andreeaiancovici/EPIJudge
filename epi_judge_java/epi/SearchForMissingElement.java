package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchForMissingElement {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class DuplicateAndMissing {
    public Integer duplicate;
    public Integer missing;

    public DuplicateAndMissing(Integer duplicate, Integer missing) {
      this.duplicate = duplicate;
      this.missing = missing;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      DuplicateAndMissing that = (DuplicateAndMissing)o;

      if (!duplicate.equals(that.duplicate)) {
        return false;
      }
      return missing.equals(that.missing);
    }

    @Override
    public int hashCode() {
      int result = duplicate.hashCode();
      result = 31 * result + missing.hashCode();
      return result;
    }

    @Override
    public String toString() {
      return "duplicate: " + duplicate + ", missing: " + missing;
    }
  }

  @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")

  public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
    DuplicateAndMissing duplicateAndMissing = new DuplicateAndMissing(0, 0);
    int initXor = 0, currXor = 0;
    for (int i = 0; i < A.size(); i++) {
      initXor ^= i;
      currXor ^= A.get(i);
    }
    int xor = initXor ^ currXor;
    int count = 0;
    while ((xor >>> count & 1) != 1) {
      count++;
    }
    int initXorPart = 0, currXorPart = 0;
    for (int i = 0; i < A.size(); i++) {
      if ((i >>> count & 1) == 1) {
        initXorPart ^= i;
      }
      if ((A.get(i) >>> count & 1) == 1) {
        currXorPart ^= A.get(i);
      }
    }
    int n = initXorPart ^ currXorPart;
    int m = xor ^ n;
    for (Integer integer : A) {
      if (integer == n) {
        duplicateAndMissing.duplicate = n;
        duplicateAndMissing.missing = m;
        break;
      }
      if (integer == m) {
        duplicateAndMissing.duplicate = m;
        duplicateAndMissing.missing = n;
        break;
      }
    }
    return duplicateAndMissing;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMissingElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
