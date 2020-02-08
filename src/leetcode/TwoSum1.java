package leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> before = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            Integer idx = before.get(need);
            if(idx == null) {
                before.put(nums[i], i);
                continue;
            }
            return new int[]{idx, i};
        }

        throw new RuntimeException("no result found");

    }


}
