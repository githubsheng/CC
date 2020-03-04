package leetcode;
import java.util.*;

public class Problem983 {

    public int mincostTickets(int[] days, int[] costs) {

        int[] passes = new int[]{1, 7, 30};
        int[] dp = new int[days.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int i = 0; i < days.length; i++) {
            for(int d = 0; d < 3; d++) {
                int r = passes[d];

                int ec = days[i] - r + 1; //ec: earliest day covered

                if(days[0] >= ec) {
                    dp[i] = Math.min(dp[i], costs[d]);
                    break;
                } else {
                    int t;
                    int iec = Arrays.binarySearch(days, ec);
                    if(iec >= 0) {
                        t = iec - 1;
                    } else {
                        // iec = -(t + 1) - 1
                        // iec = -t -1 -1 = -t -2
                        // t = -iec - 2;
                        t = -iec - 2;
                    }
                    dp[i] = Math.min(dp[t] + costs[d], dp[i]) ;
                }
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        Problem983 sol = new Problem983();
        int ret = sol.mincostTickets(new int[]{1,4,6,7,8,20}, new int[]{2,7,15});
        System.out.println(ret);
    }

}
