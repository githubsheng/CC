import references.output.OutputUtil;

public class SingleNumberIII260 {

    public int[] singleNumber(int[] nums) {

        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            diff ^= nums[i];
        }

        int firstDifferentDigitMask = diff ^ (diff - 1) & diff;

        int ones = 0;
        int zeros = 0;

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if ((cur & firstDifferentDigitMask) == 0) {
                zeros ^= cur;
            } else {
                ones ^= cur;
            }
        }

        return new int[]{ones, zeros};
    }

    public static void main(String[] args) {
        SingleNumberIII260 sol = new SingleNumberIII260();
        int[] ret = sol.singleNumber(new int[]{1,2,1,-3,2,5});
        OutputUtil.printArray(ret);

        int[] ret2 = sol.singleNumber(new int[]{3, 0, 1, 1, 2, 2});
        OutputUtil.printArray(ret2);
    }

}
