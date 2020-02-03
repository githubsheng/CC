import java.util.Arrays;

//timout
public class Problem1000 {

    int[] sums = null;
    int k;
    int[] stones = null;
    int[][][] cache;

    public int mergeStones(int[] stones, int K) {
        this.k = K;
        this.stones = stones;
        buildSum();
        buildCache();
        return formGroups(1, 0, this.stones.length - 1);
    }


    private int formGroups(int groups, int start, int end) {
        if (groups < 1) return -1;
        int count = end - start + 1;
        if (groups > count) return -1; //-1 not possible
        int mergeCost = sums[end] - sums[start] + stones[start];

        //no need to form groups, nor merge
        if (groups == count) return 0;

        int cached = cache[groups][start][end];
        if( cached != -2) return cached;

        //if needs to be 1, then first we need to form k groups, and then merge.
        if (groups == 1) {
            int formCost = formGroups(k, start, end);
            if (formCost < 0) return -1;
            int totalCost = mergeCost + formCost;
            cache[groups][start][end] = totalCost;
            return totalCost;
        }

        int minFormCost = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int cost1 = formGroups(1, start, i);
            int cost2 = formGroups(groups - 1, i + 1, end);
            if(cost1 < 0 || cost2 < 0) continue;
            minFormCost = Math.min(minFormCost, cost1 + cost2);
        }
        if(minFormCost == Integer.MAX_VALUE) minFormCost = -1;
        cache[groups][start][end] = minFormCost;
        return minFormCost;
    }

    private void buildSum(){
        sums = new int[stones.length];
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
            sums[i] = sum;
        }
    }

    private void buildCache(){
        cache = new int[k + 1][stones.length][stones.length];
        for (int i = 0; i < k + 1; i++) {
            for (int j = 0; j < stones.length; j++) {
                int[] t = cache[i][j];
                Arrays.fill(t, -2);
            }
        }
    }

    public static void main(String[] args) {
        Problem1000 sol = new Problem1000();
        int ret = sol.mergeStones(new int[]{59,7,10,6,3,7,59,20,40,86,18,43,78,83,95,27,25,80,96,29,21}, 3);
        System.out.println(ret);
    }

}