package leetcode;

/*

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

entention
execution

extention
execution

exention
execution

exection
execution

execution
execution


 */
public class EditDistance72 {

    public int minDistance(String word1, String word2) {
        int[][] cache = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i < cache.length; i++) {
            for(int j = 0; j < cache[0].length; j++)
                cache[i][j] = -1;
        }
        return helper(word1, word2, 0, 0, cache);
    }


    private int helper(String word1, String word2, int matchIdx, int modifyIdx, int[][] cache) {

        if(matchIdx == word1.length()) {
            //we have modified the word2 to match entire word1, for the remaining parts of word2, we need to remove them.
            return word2.length() - modifyIdx;
        }

        if(modifyIdx == word2.length()) {
            //we have deleted or replaced all letters in word2, now we can only add new letters
            //we need to insert letters to match the rest part of word1.
            return word1.length()- matchIdx;
        }

        if(cache[matchIdx][modifyIdx] != -1) {
            return cache[matchIdx][modifyIdx];
        }

        //try to modify word2 to match word1
        int target = word1.charAt(matchIdx);
        int current = word2.charAt(modifyIdx);
        if(target == current) {
            //no edit needed
            int ret = helper(word1, word2, matchIdx + 1, modifyIdx + 1, cache);
            cache[matchIdx][modifyIdx] = ret;
            return ret;
        } else {
            int tempRet;
            int ret = Integer.MAX_VALUE;
            //try insert a letter on word2
            tempRet = helper(word1, word2, matchIdx + 1, modifyIdx, cache);
            ret = Math.min(tempRet, ret);
            //try delete the current letter on word2
            tempRet = helper(word1, word2, matchIdx, modifyIdx + 1, cache);
            ret = Math.min(tempRet, ret);
            //try replace the current letter on word2 with target letter
            tempRet = helper(word1, word2, matchIdx + 1, modifyIdx + 1, cache);
            ret = Math.min(tempRet, ret);
            //one edit.
            ret += 1;
            cache[matchIdx][modifyIdx] = ret;
            return ret;
        }
    }

    public static void main(String[] args) {
        EditDistance72 sol = new EditDistance72();
        int ret = sol.minDistance("horse", "ros");
        System.out.println(ret);

        EditDistance72 sol2 = new EditDistance72();
        int ret2 = sol2.minDistance("", "avv");
        System.out.println(ret2);

    }


}
