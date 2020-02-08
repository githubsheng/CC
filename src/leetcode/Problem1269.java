package leetcode;

public class Problem1269 {

    public int numWays(int steps, int arrLen) {

        if(arrLen == 0) return 0;
        if(steps == 0) return 0;

        int[] dirs = new int[]{-1, 0, 1};
        int mod = 1000000000 + 7;

        int maxIdx = Math.min(arrLen - 1, steps);

        int[] dp = new int[arrLen];
        dp[0] = 1;

        for (int i = 1; i <= steps; i++) {

            int[] t = new int[arrLen];

            for (int j = 0; j <= maxIdx; j++) {
                int ret = 0;
                if(j > 0 && j < maxIdx) {
                    for (int k = 0; k < 3; k++) {
                        ret = (ret + dp[j + dirs[k]]) % mod;
                    }
                } else if (j == 0) {
                    ret = (ret + dp[1]) % mod;
                    ret = (ret + dp[0]) % mod;
                } else {
                    // j = maxIdx
                    ret = (ret + dp[maxIdx - 1]) % mod;
                    ret = (ret + dp[maxIdx]) % mod;
                }

                t[j] = ret;
            }

            dp = t;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        Problem1269 sol = new Problem1269();
        int ret = sol.numWays(4, 2);
        System.out.println(ret);
    }


}
