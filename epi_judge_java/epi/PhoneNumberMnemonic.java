package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {
  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")

  public static List<String> phoneMnemonic(String phoneNumber) {
    Set<String> results = new HashSet<>();
    recursivePhoneMnemonic(results, phoneNumber, 0, new StringBuilder());
    return new ArrayList<>(results);
  }

  private static void recursivePhoneMnemonic(Set<String> results, String phoneNumber, int index, StringBuilder sb) {
    if (index == phoneNumber.length()) {
      results.add(sb.toString());
    } else {
      for (int j = 0; j < 4; j++) {
        String letter = getLetter(phoneNumber.substring(index, index + 1), j);
        if (letter != null) {
          sb.append(letter);
          recursivePhoneMnemonic(results, phoneNumber, index + 1, sb);
          sb.delete(sb.lastIndexOf(letter), sb.length());
        }
      }
    }
  }

  private static String getLetter(String number, int index) {
    switch (number) {
      case "0": return "0";
      case "1": return "1";
      case "2": {
        switch (index) {
          case 0:
            return "A";
          case 1:
            return "B";
          case 2:
            return "C";
          default:
            return null;
        }
      }
      case "3": {
        switch (index) {
          case 0:
            return "D";
          case 1:
            return "E";
          case 2:
            return "F";
          default:
            return null;
        }
      }
      case "4": {
        switch (index) {
          case 0:
            return "G";
          case 1:
            return "H";
          case 2:
            return "I";
          default:
            return null;
        }
      }
      case "5": {
        switch (index) {
          case 0:
            return "J";
          case 1:
            return "K";
          case 2:
            return "L";
          default:
            return null;
        }
      }
      case "6": {
        switch (index) {
          case 0:
            return "M";
          case 1:
            return "N";
          case 2:
            return "O";
          default:
            return null;
        }
      }
      case "7": {
        switch (index) {
          case 0:
            return "P";
          case 1:
            return "Q";
          case 2:
            return "R";
          case 3:
            return "S";
          default:
            return null;
        }
      }
      case "8": {
        switch (index) {
          case 0:
            return "T";
          case 1:
            return "U";
          case 2:
            return "V";
          default:
            return null;
        }
      }
      case "9": {
        switch (index) {
          case 0:
            return "W";
          case 1:
            return "X";
          case 2:
            return "Y";
          case 3:
            return "Z";
          default:
            return null;
        }
      }
      default:
        return null;
    }
  }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
