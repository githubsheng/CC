public class Problem1277 {

    public int countSquares(int[][] matrix) {
        //our total count
        int count = 0;

        //handle edge case:
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;

        //firstly, count how many 1 are there, they form a size 1 square.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) count++;
            }
        }

        //in the following while loop, we repeatedly count the squares of size base + 1
        //so we start with square of size 2 (size 1 is already counted above)
        int base = 1;
        while (true) {
            //how many new squares we have discovered.
            int inc = 0;
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    /*
                        this is the main logic here. take a look at a square of size 3, what pattern can you find?
                        1 1 1
                        1 1 1
                        1 1 1 <-- focus on the bottom right cell
                        obviously the bottom right cell needs to have 1 there,
                        but we can also find another 3 squares of size 2 there.

                        in this main while loop,
                        we modify the matrix so that a cell's value stands for
                        the size of the biggest square (discovered so far) that has
                        this cell as its bottom right cell
                     */
                    if (
                            //bottom right cell needs to have 1 there, but since we use the cell to record (as discussed above )
                            //the value may not be exactly 1, eg. if it is 2, it means there is a square of size 2 that use the
                            //cell as bottom right cell, which implicitly tells that originally there must be 1 there
                            matrix[i][j] >= 1
                            //check the 3 smaller squares
                            && matrix[i - 1][j - 1] >= base
                            && matrix[i][j - 1] >= base
                            && matrix[i - 1][j] >= base) {
                        matrix[i][j] = base + 1;
                        inc++;
                    }
                }
            }

            //adds the newly found squares to our total count
            count += inc;
            //in the above logic, you may notice, at least 3 squares are needed to form a bigger square.
            //thus, we can terminate the loop if inc is less than 3: there is no way we are going to find bigger ones later.
            if (inc < 3) break;
            //increase our base, we will look up bigger square in subsequent iterations
            base++;
        }

        return count;
    }

    public static void main(String[] args) {
        Problem1277 sol = new Problem1277();

    }

}
