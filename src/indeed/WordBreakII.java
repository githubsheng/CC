package indeed;

import java.util.*;

public class WordBreakII {

    //find any break and return



    public String wordBreak(String s, List<String> wordDict) {
        int l = s.length() + 1;

        /*
        dp idx = string index + 1.
        if substring ending at i - 1 can break into a list of dict words,
        then dp[i] = this list of dict words
        if substring cannot break into..., then dp[i] = null

        dp[i] = dp[i-1] + suffix | dp[i-2] + suffix | .... | dp[0] + suffix (entire string ends at i - 1)
        */
        String[] dp = new String[l];
        //assuming entire substring is a dict word, we have dp[i] = dp[0] + suffix(1, i), so dp[0] needs to be none null.
        //think in another way, dp[0] means no prefix
        dp[0] = "";

        //i: where prefix ends
        for(int i = 0; i < s.length(); i++) {

            //check if substring ending at i, can break into...

            for(int j = -1; j < i; j++) { //j: -1, i: 3
                //j = -1 means no prefix
                String prefixComb = dp[j + 1]; //dp[0]: ""
                if(prefixComb != null) { //""
                    String suffix = s.substring(j + 1, i + 1); //substring(0, 3),  suffix: cat
                    if(wordDict.contains(suffix)) { //true
                        StringBuilder sb = new StringBuilder();
                        sb.append(prefixComb);
                        if(!prefixComb.isEmpty()) sb.append(' ');
                        sb.append(suffix);
                        dp[i + 1] = sb.toString(); //dp[4] = "cat"
                        break;
                    }


                }

            }


        }

        //substring ending at s.length() - 1, dp index = s.length() - 1 + 1
        return dp[s.length()];

    }

    public static void main(String[] args) {
        WordBreakII sol = new WordBreakII();
        String ret = sol.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
        System.out.println(ret);

        //test
        //"catsand" = "cat and"
    }


}
