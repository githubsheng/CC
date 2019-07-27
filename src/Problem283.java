import references.output.OutputUtil;

/*

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.

 */
public class Problem283 {

    public void moveZeroes(int[] nums) {

        if(nums.length == 0) return;

        int write = 0;
        int read = 0;

        for (read = 0; read < nums.length; read++) {
            if(nums[read] == 0) continue;
            nums[write] = nums[read];
            write++;
        }

        for (; write < nums.length; write++) {
            nums[write] = 0;
        }
    }

    public static void main(String[] args) {
        Problem283 sol = new Problem283();
        int[] arr = new int[]{1, 2, 3, 4, 5};
        sol.moveZeroes(arr);
        OutputUtil.printArray(arr);
    }

}
