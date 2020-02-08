package leetcode;

public class Problem1267 {


    public int countServers(int[][] grid) {

        int rowLen = grid.length;
        if(rowLen == 0) return 0;
        int colLen = grid[0].length;
        if(colLen == 0) return 0;

        int sum = 0;
        //counted in row pass
        boolean[][] counted = new boolean[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            int rc = 0;
            for (int j = 0; j < colLen; j++) {
                if(grid[i][j] == 1) rc++;
                if(rc > 1) break;
            }

            if(rc > 1) {
                for (int j = 0; j < colLen; j++) {
                    if(grid[i][j] == 1) {
                        sum++;
                        counted[i][j] = true;
                    }
                }
            }
        }

        for (int j = 0; j < colLen; j++) {
            int cc = 0;
            for (int i = 0; i < rowLen; i++) {
                if(grid[i][j] == 1) cc++;
                if(cc > 1) break;
            }

            if(cc > 1) {
                for (int i = 0; i < rowLen; i++) {
                    if(grid[i][j] == 1 && !counted[i][j]) {
                        sum++;
                    }
                }
            }
        }

        return sum;

    }


    public static void main(String[] args) {
        Problem1267 sol = new Problem1267();
    }
}
