package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    StringBuilder stringBuilder = new StringBuilder();
    if(x == 0) {
      stringBuilder.append("0");
    }
    boolean isNegative = x < 0;
    int digit;
    while(x != 0) {
      digit = x % 10;
      if(isNegative) {
        digit*=-1;
      }
      switch (digit) {
        case 0: stringBuilder.append("0"); break;
        case 1: stringBuilder.append("1"); break;
        case 2: stringBuilder.append("2"); break;
        case 3: stringBuilder.append("3"); break;
        case 4: stringBuilder.append("4"); break;
        case 5: stringBuilder.append("5"); break;
        case 6: stringBuilder.append("6"); break;
        case 7: stringBuilder.append("7"); break;
        case 8: stringBuilder.append("8"); break;
        case 9: stringBuilder.append("9"); break;
      }
      x = x / 10;
    }
    if(isNegative) {
      stringBuilder.append("-");
    }
    stringBuilder.reverse();
    return stringBuilder.toString();
  }
  public static int stringToInt(String s) {
    int value = 0;
    boolean isNegative = false;
    for(int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if(i == 0 && c == '-') {
        isNegative = true;
      }
      switch (c) {
        case '0': value+=0; break;
        case '1': value+=1; break;
        case '2': value+=2; break;
        case '3': value+=3; break;
        case '4': value+=4; break;
        case '5': value+=5; break;
        case '6': value+=6; break;
        case '7': value+=7; break;
        case '8': value+=8; break;
        case '9': value+=9; break;
      }
      if(i < s.length() - 1){
        value*=10;
      }
    }
    if(isNegative) {
      value*=-1;
    }
    return value;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
