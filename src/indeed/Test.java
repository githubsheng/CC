package indeed;

import java.util.*;

public class Test {
    int count;
    int totaldfs;
    Map<String,Integer> dfsMap = new HashMap<>();
    public float sumPossibilityButtomUpDP(int dice, int target) {
        int total = (int) Math.pow(6,dice);

        int[][] dp = new int[dice+1][target+1];
        for(int i = 1; i <= Math.min(target,6); i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= dice; i++)
            for (int j = 1; j <= target; j++)
                for (int k = 1; k <= 6 && k < j; k++)
                    dp[i][j] += dp[i-1][j-k];

        return (float)dp[dice][target]/total;
    }


    public float sumWithDFS(int dice,int target) {
        int total = (int) Math.pow(6,dice);
        dfs(dice, target);
        return (float)totaldfs/total;
    }

    public void dfs(int dice, int target) {
        if(target == 0 && dice == 0) {
            totaldfs++;
            return;
        }
        if(dice == 0 || target <= 0){
            return;
        }
        for(int i=1; i<=6;i++){
            dfs(dice-1,target-i);
        }
    }

    public   float sumwithDFSMap(int dice, int target){
        int totalsol = dfsMapApp(dice,target);
        int total = (int) Math.pow(6,dice);
        return (float)totalsol/total;
    }
    private int dfsMapApp(int dice, int target) {
        if(dice == 0 ) {
            return target ==0?1:0;
        }
        String  key =  dice+"_" + target;
        if(dfsMap.containsKey(key)){
            return dfsMap.get(key);
        }
        int result = 0;
        for(int i = 1; i <= 6;i++){
            result+=dfsMapApp(dice-1,target-i);
        }
        dfsMap.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        Test dc = new Test();
//        System.out.println(dc.sumPossibility(10,35));
//        System.out.println(dc.sumPossibilityTopDownDP(10,35));
        System.out.println(dc.sumPossibilityButtomUpDP(10,25));
        System.out.println(dc.sumWithDFS(10,25));
        System.out.println(dc.sumwithDFSMap(10,25));

    }
}
