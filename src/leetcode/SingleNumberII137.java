package leetcode;

/*
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
 */
public class SingleNumberII137 {

    public int singleNumber(int[] nums) {
        int three;
        int two = 0;
        int one = 0;

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];

            three = current & two;

            two = two | (current & one);
            one = one | current;

            two = ~three & two;
            one = ~three & one;
        }

        return one;
    }

    public static void main(String[] args) {
        SingleNumberII137 sol = new SingleNumberII137();
        int ret = sol.singleNumber(new int[]{0,1,0,1,0,1,99});
        System.out.println(ret);
        ret = sol.singleNumber(new int[]{1, 2, 1, 1, 1, 1, 1, 4, 5, 5, 5, 4, 4});
        System.out.println(ret);
        ret = sol.singleNumber(new int[]{5});
        System.out.println(ret);
    }

}
