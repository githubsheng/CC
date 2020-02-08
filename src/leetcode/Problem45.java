package leetcode;

/*

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.

 */
public class Problem45 {

    public int jump(int[] nums) {
        if(nums.length < 2) return 0;

        int dest = nums.length - 1;
        int count = 0;

        int curr = 0;
        while(count < nums.length) {
            int limit = curr + nums[curr];
            if (limit >= dest) return ++count;
            int furthest = -1;
            int next = curr;
            for (int i = curr + 1; i <= limit; i++) {
                int inc = nums[i];
                int t = i + inc; // furthest we can go from i.

                if (t > furthest) {
                    furthest = t;
                    next = i;
                }
            }
            curr = next;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Problem45 sol = new Problem45();
        int ret = sol.jump(new int[]{2, 3, 1, 1, 4});
        System.out.println(ret);
    }

}
