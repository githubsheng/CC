public class RangeSumQuery2dImmutable304 {

    private int[][] m;
    private int rl;
    private int cl;
    private int[][] topLeft;
    private int[][] bottomLeft;
    private int[][] topRight;
    private int[][] bottomRight;

    public RangeSumQuery2dImmutable304(int[][] matrix) {
        m = matrix;
        rl = matrix.length;
        cl = rl == 0 ? 0: matrix[0].length;
        topLeft = new int[rl][cl];
        bottomLeft = new int[rl][cl];
        topRight = new int[rl][cl];
        bottomRight = new int[rl][cl];

        //init topleft
        for (int i = 0; i < rl; i++) {
            int rs = 0; //sum along the row
            for (int j = 0; j < cl; j++) {
                rs += m[i][j];
                if(i == 0) {
                    topLeft[i][j] = rs;
                } else {
                    topLeft[i][j] = topLeft[i - 1][j] + rs;
                }
            }
        }

        for (int i = 0; i < rl; i++) {
            int rs = 0;
            for (int j = cl - 1; j > -1 ; j--) {
                rs += m[i][j];
                if(i == 0) {
                    topRight[i][j] = rs;
                } else {
                    topRight[i][j] = topRight[i - 1][j] + rs;
                }
            }
        }


        for (int i = rl - 1; i > -1; i--) {
            int rs = 0; //sum along the row
            for (int j = 0; j < cl; j++) {
                rs += m[i][j];
                if(i == rl - 1) {
                    bottomLeft[i][j] = rs;
                } else {
                    bottomLeft[i][j] = bottomLeft[i + 1][j] + rs;
                }
            }
        }

        for (int i = rl - 1; i > -1; i--) {
            int rs = 0; //sum along the row
            for (int j = cl - 1; j > -1 ; j--) {
                rs += m[i][j];
                if(i == rl - 1) {
                    bottomRight[i][j] = rs;
                } else {
                    bottomRight[i][j] = bottomRight[i + 1][j] + rs;
                }
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(rl == 0 || cl == 0) return 0;
//        if(row1 == row2 || col1 == col2) return 0;
        int r1 = 0;
        if(row1 > 0 ) r1 = topLeft[row1 - 1][col2];
        int r2 = 0;
        if(col2 < cl - 1) r2 = topRight[row2][col2 + 1];
        int r3 = 0;
        if(row2 < rl - 1) r3 = bottomRight[row2 + 1][col1];
        int r4 = 0;
        if(col1 > 0) r4 = bottomLeft[row1][col1 - 1];
        return topLeft[rl -1][cl - 1] - r1 - r2 - r3 - r4;
    }

    /*

    [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]

     */
    public static void main(String[] args) {
//        int[][] m = new int[5][5];
//        int[] r1 = new int[]{3,0,1,4,2};
//        int[] r2 = new int[]{5,6,3,2,1};
//        int[] r3 = new int[]{1,2,0,1,5};
//        int[] r4 = new int[]{4,1,0,1,7};
//        int[] r5 = new int[]{1,0,3,0,5};
//        m[0] = r1;
//        m[1] = r2;
//        m[2] = r3;
//        m[3] = r4;
//        m[4] = r5;
//
//        int total = 0;
//        for (int i = 0; i < r1.length; i++) {
//            total += r1[i];
//            total += r2[i];
//            total += r3[i];
//            total += r4[i];
//            total += r5[i];
//        }
//        System.out.println(total);
        int[][] m = new int[1][1];
        m[0][0] = -1;

        RangeSumQuery2dImmutable304 sol = new RangeSumQuery2dImmutable304(m);
        System.out.println(sol.topLeft[sol.rl- 1][sol.cl - 1]);
        System.out.println(sol.topRight[sol.rl - 1][0]);
        System.out.println(sol.bottomRight[0][0]);
        System.out.println(sol.bottomLeft[0][sol.cl - 1]);

        int ret = sol.sumRegion(0, 0, 0, 0);
        System.out.println(ret);
    }

}
