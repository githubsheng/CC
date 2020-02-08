package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangsheng on 2/12/18.
 */
public class FindAllAnagramsInAString438 {

    public List<Integer> findAnagrams(String s, String p) {

        if(s.length() < p.length()) return Collections.emptyList();
        List<Integer> ret = new ArrayList<>(s.length());

        int[] charCounts = new int[26];
        int[] expected = new int[26];
        int offset = 97;

        for(int i = 0, l = p.length(); i < l; i++) {
            int c = p.charAt(i) - offset;
            expected[c] += 1;
        }

        for(int i = 0, l = p.length(); i < l; i++) {
            int c = s.charAt(i) - offset;
            charCounts[c] += 1;
        }

        int correctCount = 0;
        for(int i = 0, l = charCounts.length; i < l; i++) {
            if(charCounts[i] == expected[i]) correctCount++;
        }
        if(correctCount == 26) ret.add(0);

        for(int i = p.length(), l = s.length(), ph = 0; i < l; i++, ph++) {
            int c = s.charAt(i) - offset;
            int prev = s.charAt(ph) - offset;

            if(prev == c) {
                if(correctCount == 26) {
                    ret.add(ph + 1);
                }
                continue;
            }

            boolean wasCorrectC = charCounts[c] == expected[c];
            charCounts[c] += 1;
            boolean isCorrectC = charCounts[c] == expected[c];

            if(wasCorrectC && !isCorrectC) {
                correctCount--;
            } else if (!wasCorrectC && isCorrectC) {
                correctCount++;
            }


            boolean wasCorrectP = charCounts[prev] == expected[prev];
            charCounts[prev] -= 1;
            boolean isCorrectP = charCounts[prev] == expected[prev];

            if(wasCorrectP && !isCorrectP) {
                correctCount--;
            } else if (!wasCorrectP && isCorrectP) {
                correctCount++;
            }


            if(correctCount == 26) ret.add(ph + 1);
        }

        return ret;
    }

    public static void main(String args[]) {

        FindAllAnagramsInAString438 sol = new FindAllAnagramsInAString438();
        List<Integer> ret = sol.findAnagrams("abab", "ab");
        for(int s : ret) {
            System.out.println(s);
        }
    }

}
