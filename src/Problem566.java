import references.input.InputUtil;
import references.output.OutputUtil;

public class Problem566 {

    public int[][] matrixReshape(int[][] nums, int r, int c) {

        if(nums.length == 0) return nums;

        int or = nums.length;
        int oc = nums[0].length;

        if(r * c != or * oc) return nums;

        int[][] ret = new int[r][c];

        int ri = 0;
        int ci = 0;

        for (int i = 0; i < or; i++) {
            for (int j = 0; j < oc; j++) {
                int elem = nums[i][j];
                ret[ri][ci] = elem;
                ci++;
                if(ci == c) {
                    ci = 0;
                    ri++;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Problem566 sol = new Problem566();
        int[][] ret = sol.matrixReshape(InputUtil.parseToNestedArrays("[[1,2],[3,4]]"), 1, 4);
        System.out.println(1);
    }

}
