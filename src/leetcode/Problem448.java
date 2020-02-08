package leetcode;

import references.output.OutputUtil;

import java.util.ArrayList;
import java.util.List;

public class Problem448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            int idx = val - 1;
            if(nums[idx] > 0) nums[idx] = -nums[idx];
        }

        List<Integer> ret = new ArrayList<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) ret.add(i + 1);
        }

        return ret;
    }

    public static void main(String[] args) {
        Problem448 sol = new Problem448();
        List<Integer> ret = sol.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1});
        OutputUtil.printList(ret);
    }

}
