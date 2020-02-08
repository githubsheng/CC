package leetcode;

public class FirstMissingPositive41 {

    public int firstMissingPositive(int[] nums) {

        if (nums.length == 0) return 1;

        int lowerLimit = 1;
        int upperLimit = nums.length;

        int wayBigger = upperLimit + 1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < lowerLimit)
                nums[i] = wayBigger;
        }

        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            if (val > upperLimit) continue;
            int idx = val - 1;
            if (nums[idx] < 0) continue;
            nums[idx] = nums[idx] * -1;
        }

        for (int i = 1; i <= upperLimit; i++) {
            if(nums[i - 1] > 0) return i;
        }

        return upperLimit + 1;
    }


    public static void main(String[] args) {
        FirstMissingPositive41 sol = new FirstMissingPositive41();
//        int ret1 = sol.firstMissingPositive(new int[]{3,4,-1,1});
//        System.out.println(ret1);

        int ret2 = sol.firstMissingPositive(new int[]{1, 1});
        System.out.println(ret2);

//        int ret3 = sol.firstMissingPositive(new int[]{1, 2, 3});
//        System.out.println(ret3);
    }

}
