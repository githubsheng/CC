public class HouseRobber198 {

    public int rob(int[] nums) {

        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] maxProfit = new int[nums.length];
        maxProfit[0] = nums[0];
        //allow to rob any house whose index is smaller than 1
        maxProfit[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            //if rob house i
            int p1 = maxProfit[i - 2] + nums[i];
            //if not rob house i
            int p2 = maxProfit[i - 1];
            maxProfit[i] = Math.max(p1, p2);
        }

        return maxProfit[nums.length - 1];
    }

    public static void main(String[] args) {
        HouseRobber198 sol = new HouseRobber198();
        int ret = sol.rob(new int[]{2,7,9,3,1});
        System.out.println(ret);

        int ret2 = sol.rob(new int[]{1, 2});
        System.out.println(ret2);
    }

}
