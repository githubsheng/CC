package leetcode;

import references.input.InputUtil;

public class Problem1266 {


    public int minTimeToVisitAllPoints(int[][] points) {


        int sum = 0;
        if(points.length <= 1) return 0;

        for (int i = 1; i < points.length; i++) {
            int[] prevPoint = points[i-1];
            int[] point = points[i];
            int h = Math.abs(point[0] - prevPoint[0]);
            int v = Math.abs(point[1] - prevPoint[1]);
            sum += h + v - Math.min(h, v);
        }

        return sum;

    }

    public static void main(String[] args) {
        Problem1266 sol = new Problem1266();

        int ret = sol.minTimeToVisitAllPoints(InputUtil.parseToNestedArrays("[[1,1],[3,4],[-1,0]]"));
        System.out.println(ret);
    }

}
