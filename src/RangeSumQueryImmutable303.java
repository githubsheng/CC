/**
 * Created by wangsheng on 2/12/18.
 */
public class RangeSumQueryImmutable303 {

    private int[] sums;

    public RangeSumQueryImmutable303(int[] nums) {
        sums = new int[nums.length];
        if(nums.length == 0) return;

        int sum = nums[0];
        sums[0] = sum;

        for(int i = 1, l = nums.length; i < l; i++) {
            sum += nums[i];
            sums[i] = sum;
        }
    }

    public int sumRange(int i, int j) {
        if(i == 0) return sums[j];
        return sums[j] - sums[i - 1];
    }



}
