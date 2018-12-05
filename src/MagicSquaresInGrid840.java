/**
 * Created by wangsheng on 2/12/18.
 */
public class MagicSquaresInGrid840 {

    public int numMagicSquaresInside(int[][] grid) {

        int maxRowStart = grid.length - 3;
        int maxColStart = grid[0].length -3;

        if(maxRowStart < 0 || maxColStart < 0) return 0;

        int count = 0;
        for(int i = 0; i <= maxRowStart; i++) {
            for(int ii = 0; ii <= maxRowStart; ii++) {
                if(isMagicSquare(grid, i, ii)) count++;
            }
        }

        return count;

    }

    private boolean isMagicSquare(int[][] grid, int row, int col) {

        int sum = grid[row][col] + grid[row][col+1] + grid[row][col+2];

        for(int r = row; r < row + 3; r++) {
            int sumOfRow = 0;
            for(int c = col; c < col + 3; c++) {
                int n = grid[r][c];
                if(n < 1 || n > 9) return false;
                sumOfRow += grid[r][c];
            }
            if(sumOfRow != sum) return false;
        }

        for(int c = col; c < col + 3; c++) {
            int sumOfCol = 0;

            for(int r = row; r < row + 3; r++) {
                int n = grid[r][c];
                if(n < 1 || n > 9) return false;
                sumOfCol += grid[r][c];
            }
            if(sumOfCol != sum) return false;
        }


        return true;
    }

    public static void main(String args[]) {

        MagicSquaresInGrid840 solution = new MagicSquaresInGrid840();
        int[] row1 = new int[]{3, 8, 4};
        int[] row2 = new int[]{5, 1, 9};
        int[] row3 = new int[]{2, 7, 6};

        int[][] test = new int[][]{row1, row2, row3};

        int ret = solution.numMagicSquaresInside(test);
        System.out.println(ret);

    }


}
