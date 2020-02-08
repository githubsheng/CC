package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//23:01 - 23:18
public class Problem139 {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        int sLen = s.length();
        boolean[] canBreak = new boolean[sLen];
        canBreak[0] = dict.contains(s.substring(0, 1));

        for (int i = 1; i < sLen; i++) {
            //first check if [0 - i] as a whole is in dict
            String whole = s.substring(0, i + 1);
            if(wordDict.contains(whole)) {
                canBreak[i] = true;
                continue;
            }

            //if not, lets split it, and see if the parts are in dict.
            for (int j = 0; j < i; j++) {
                //left part: canBreak[j]
                String lastPart = s.substring(j + 1, i + 1);
                if(canBreak[j] && dict.contains(lastPart)) {
                    canBreak[i] = true;
                    break;
                }
            }
        }

        return canBreak[sLen - 1];
    }

    public static void main(String[] args) {
        Problem139 sol = new Problem139();
        boolean ret = sol.wordBreak("applepenapple", Arrays.asList("apple", "pen"));
        System.out.println(ret);
    }

}
