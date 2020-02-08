package leetcode;

class Problem287 {
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        if (len == 2) return nums[0];
        int r = len - 1;
        int l = 1;
        int max = r;

        while(l < r) {
            int m = (l + r) / 2;
            int el = m - 1;
            int er = max - m;

            int al = 0;
            int ar = 0;
            int ae = 0;

            for(int a : nums) {
                if (a < m) {
                    al++;
                } else if (a > m) {
                    ar++;
                } else {
                    ae++;
                }
            }

            if(ae > 1) return m;
            if(al > el) r = m - 1;
            if(ar > er) l = m + 1;
        }

        return l;
    }

    public static void main(String[] args) {
        Problem287 sol = new Problem287();
        int ret = sol.findDuplicate(new int[]{8,1,1,1,2,7,4,3,1,1});
        System.out.println(ret);
    }

}