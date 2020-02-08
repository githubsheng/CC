package leetcode;

import java.util.Arrays;

public class Problem91_2 {

    private int[] cache;

    public int numDecodings(String s) {
        if(s.length() == 0) return 0;
        cache = new int[s.length()];
        Arrays.fill(cache, -1);
        return helper(s, 0);
    }

    private int helper(String s, int start) {
        if(start == s.length()) return 1;
        if(start > s.length()) return 0;
        if(cache[start] != -1) return cache[start];

        int c = 0;

        if(start + 1 <= s.length()) {
            String sub = s.substring(start, start + 1);
            if(isValid(sub)) {
                int t = helper(s, start + 1);
                c += t;
            }
        }

        if(start + 2 <= s.length()) {
            String sub = s.substring(start, start + 2);
            if(isValid(sub)) {
                int t =  helper(s, start + 2);
                c += t;
            }
        }

        cache[start] = c;

        return c;
    }

    private boolean isValid(String sub) {
        if(sub.charAt(0) == '0') return false;
        int t = Integer.parseInt(sub);
        return t > 0 && t < 27;
    }

    public static void main(String[] args) {
        Problem91_2 sol = new Problem91_2();
        int ret = sol.numDecodings("1230252720");
        System.out.println(ret);
    }

}
