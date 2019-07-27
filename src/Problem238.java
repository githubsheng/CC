import references.input.InputUtil;
import references.output.OutputUtil;

public class Problem238 {

    public int[] productExceptSelf(int[] nums) {

        if(nums.length == 0) return nums;
        if(nums.length == 1) return new int[]{0};
        if(nums.length == 2) return new int[]{nums[1], nums[0]};

        int[] ret = new int[nums.length];
        int lastIdx = nums.length - 1;
        ret[lastIdx] = nums[lastIdx];
        for (int i = nums.length - 2; i > -1; i--) {
            ret[i] = ret[i+1] * nums[i];
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] * nums[i];
        }

        ret[0] = ret[1];
        for (int i = 1; i < lastIdx; i++) {
            ret[i] = nums[i - 1] * ret[i + 1];
        }
        ret[lastIdx] = nums[lastIdx - 1];
        return ret;
    }

    public static void main(String[] args) {
        Problem238 sol = new Problem238();
        int[] ret = sol.productExceptSelf(new int[]{1, 2, 3});
        OutputUtil.printArray(ret);
    }




}
