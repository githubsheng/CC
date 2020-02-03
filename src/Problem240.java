//13:42
public class Problem240 {

    public boolean searchMatrix(int[][] matrix, int target) {

        int rowIdx = matrix.length - 1;
        int colIdx = 0;
        int rowSize = matrix.length;
        if(rowSize == 0) return false;
        int colSize = matrix[0].length;
        if(colSize == 0) return false;

        while(rowIdx >=0 && colIdx < colSize) {
            int val = matrix[rowIdx][colIdx];
            if(val > target) {
                colIdx++;
            } else if (val < target) {
                rowIdx--;
            } else {
                return true;
            }
        }

        return false;
    }

}
