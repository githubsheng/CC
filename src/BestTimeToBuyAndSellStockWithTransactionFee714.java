import java.util.LinkedList;

public class BestTimeToBuyAndSellStockWithTransactionFee714 {

    public int maxProfit(int[] prices, int fee) {

        int maxProfit = 0;
        LinkedList<Integer> ascendingParts = new LinkedList<>();

        int startOfAscend = -1;
        for(int i = 0, l = prices.length - 1; i < l; i++) {
            if(prices[i + 1] > prices[i] && startOfAscend == -1) {
                //ascending && haven't recorded the start of the ascend
                startOfAscend = i;
            } else if (prices[i + 1] <= prices[i] && startOfAscend != -1) {
                //start to descend, we have reached the end of an ascending part
                int startVal = prices[startOfAscend];
                int endVal = prices[i];
                ascendingParts.addLast(startVal);
                ascendingParts.addLast(endVal);
                startOfAscend = -1;
            }
        }

        //edge case, in the above for loop, last element is not properly considered
        if(startOfAscend != -1) {
            int startVal = prices[startOfAscend];
            int endVal = prices[prices.length - 1];
            ascendingParts.addLast(startVal);
            ascendingParts.addLast(endVal);
        }

        //max profit is 0
        if(ascendingParts.isEmpty()) return maxProfit;

        int buy = ascendingParts.removeFirst();
        int sell = ascendingParts.removeFirst();

        while(!ascendingParts.isEmpty()) {
            int _buy = ascendingParts.removeFirst();
            int _sell = ascendingParts.removeFirst();

            if(sell - _buy - fee >= 0) {
                //sell (if it makes profit) and buy again..
                int inc = sell - buy - fee;
                maxProfit += Math.max(inc, 0);
                buy = _buy;
                sell = _sell;
            } else {
                buy = Math.min(buy, _buy);
                sell = Math.max(sell, _sell);
            }
        }

        int inc = sell - buy - fee;
        maxProfit += Math.max(inc, 0);

        return maxProfit;

    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithTransactionFee714 sol = new BestTimeToBuyAndSellStockWithTransactionFee714();

        int[] input1 = new int[]{1036,2413,2776,825,2640,31,1560,2917,4282,783,3146,2600,1939,694,4284,3881,554,167,372,4620,3037,1175,1075,3845,4981,4495,3406,4228,2807,4774,4526,3914,2633,3762,1570,2334,616,1648,1914,2900,349,2428,4013,1964,4020,1882,629,240,2595,2902,3419,292,224,4437,4918,632,3701,3840,3996,2129,3345,3979,1954,781,1576,1084,3250,4517,3106,2133,309,4520,2225,4366,4628,1303,4373,1266,3181,558,3855,3447,4335,2115,4603,661,1715,3972,2846,342,686,787,273,2575,100,2860,3587,4236,3862,2238,3471,3123,431,4489,1551,596,4037,4986,594,2386,326,628,1363,2377,4986,3780,3853,2670,2852,3519,2998,4083,3392};
        int ret1 = sol.maxProfit(input1, 655);
        System.out.println(ret1);

    }

}
