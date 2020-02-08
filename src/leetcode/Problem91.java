package leetcode;

/*

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

 */
public class Problem91 {

    public int numDecodings(String s) {
        if(s.length() == 0) return 0;
        if(s.length() == 1) return s.charAt(0) == '0' ? 0 : 1;

        s = "99" + s;

        int[] ways = new int[s.length()];
        ways[0] = 1;
        ways[1] = 1;

        for (int i = 2; i < s.length(); i++) {
            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);
            if(prev == '0') {
                if(curr == '0') return 0;
                ways[i] = ways[i - 1];
            } else {
                if(prev == '1') {
                    if(curr == '0') {
                        ways[i] = ways[i - 2];
                    } else {
                        ways[i] = ways[i - 1] + ways[i - 2];
                    }
                } else if (prev == '2') {
                    if(curr == '0') {
                        ways[i] = ways[i - 2];
                    } else if (curr < '7'){
                        ways[i] = ways[i - 1] + ways[i - 2];
                    } else {
                        ways[i] = ways[i-1];
                    }
                } else {
                    //prev is 3 or bigger
                    if(curr == '0') {
                        return 0;
                    } else {
                        ways[i] = ways[i -1];
                    }
                }
            }
        }
        return ways[s.length() - 1];
    }

    public static void main(String[] args) {
        Problem91 sol = new Problem91();
        int ret = sol.numDecodings("110");
        System.out.println(ret);
    }

}
