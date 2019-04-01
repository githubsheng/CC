public class SingleElementInSortedArray540 {

    /*

    0 1 2 3 4 5 6 7 8 9 10  11 12 13  14
    1,1,2,3,3,4,4,8,8,9, 9, 5, 5, 7,  7

    0,1,2,3,4, 5, 6
    3,3,7,7,10,11,11

     */
    public int singleNonDuplicate(int[] nums) {

        if(nums.length == 1) return nums[0];


        int start = 0;
        int last = nums.length - 1;

        while (true) {
            if (last == start) return nums[start];
            int mid = (last - start) / 2 + start;
            int base;

            if (nums[mid] == nums[mid + 1]) {
                base = mid + 1;
            } else if (nums[mid] == nums[mid - 1]) {
                base = mid;
            } else {
                return nums[mid];
            }

            int rightHandCount = last - base;


            if (rightHandCount % 2 == 0) {
                //if count is even, target is on the left
                last = base - 2;
            } else {
                //if count is odd, target is on the right;
                start = base + 1;
            }
        }
    }

    public static void main(String[] args) {
        SingleElementInSortedArray540 sol = new SingleElementInSortedArray540();
        int[] input1 = new int[]{3, 3, 7, 7, 10, 11, 11};
        int ret = sol.singleNonDuplicate(input1);
        System.out.println(ret);

        int[] input2 = new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8};
        ret = sol.singleNonDuplicate(input2);
        System.out.println(ret);

        int[] input3 = new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8, 9, 9, 5, 5, 7, 7};
        ret = sol.singleNonDuplicate(input3);
        System.out.println(ret);

        int[] input4 = new int[]{1, 1, 2};
        ret = sol.singleNonDuplicate(input4);
        System.out.println(ret);

        int[] input5 = new int[]{1, 2, 2};
        ret = sol.singleNonDuplicate(input5);
        System.out.println(ret);
    }


}
