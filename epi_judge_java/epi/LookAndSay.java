package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {
    @EpiTest(testDataFile = "look_and_say.tsv")

    public static String lookAndSay(int n) {
        String s = "1";
        for (int i = 0; i < n - 1; i++) {
            s = nextNumber(s);
        }
        return s;
    }

    //312211
    private static String nextNumber(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int i = 0;
        char prev = ' ';
        while (i < s.length()) {//0 < 6, 1 < 6, 2 < 6, 3 < 6, 4 < 6, 5 < 6
            char c = s.charAt(i);//3,1,2,2,1,1
            if (prev != ' ' && c != prev) {
                sb.append(count);
                sb.append(prev);//131122
                count = 1;
            } else {
                count++;//1,2,2
            }
            prev=c;//3,1,2,2,1
            i++;//1,2,3,4,5,6
        }
        sb.append(count);
        sb.append(prev);//13112221
        return sb.toString();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
