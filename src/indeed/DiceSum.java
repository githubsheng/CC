package indeed;

public class DiceSum {

    /*
     写一个函数float sumPossibility(int dice, int target)，就是投dice个骰子，求最后和为target的概率。因为总共的可能性是6^dice，所以其实就是combination sum，
     */
    public double sol(int n /*number of dices*/, int t /*target*/) {
        //dp[t][n] = dp[t-1][n-1] + dp[t-2][n-1] .... + dp[t-6][n-1]
        //dp[x][1] = 1, x: 1 -> 6

        int[][] dp = new int[t + 1][n + 1];

        //base case
        for(int s = 1; s < 7; s++) {
            //for 1 dice, # of comb for value 1 ~ 6 is 1.
            dp[s][1] = 1;
        }

        //s: sum
        for(int s = 1; s <= t; s++) {
            //d: dices
            for(int d = 2; d <= n; d++) {
                //v: value of last dice
                for(int v = 1; v < 7; v++) {
                    int r = s - v; //remain
                    if(r <= 0) continue; //avoid index out of bound
                    dp[s][d] += dp[r][d-1];
                }
            }
        }

        int combs = dp[t][n];
        double total = Math.pow(6, n);
        return combs/total;
    }


    public static void main(String[] args) {
        DiceSum cs = new DiceSum();
        System.out.println(cs.sol(10, 25));
    }



}
