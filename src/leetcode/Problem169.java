package leetcode;

import java.util.Arrays;

public class Problem169 {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

}
