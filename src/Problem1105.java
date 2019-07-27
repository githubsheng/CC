import references.input.InputUtil;

public class Problem1105 {

    public int minHeightShelves(int[][] books, int shelf_width) {

        int[][] mh = new int[books.length][2];

        mh[0][0] = books[0][1];
        mh[0][1] = shelf_width - books[0][0];

        for(int i = 1; i < books.length; i++) {

            int minHeight = Integer.MAX_VALUE;

            int rowSpace = shelf_width;
            int rowHeight = 0, totalHeight;

            for(int j = i; j >= 0; j--) {
                int bw = books[j][0];
                int bh = books[j][1];
                rowSpace -= bw;
                if(rowSpace < 0) break;

                rowHeight = Math.max(rowHeight, bh);
                int prevMinRowHeight = j == 0 ? 0 : mh[j - 1][0];
                totalHeight = rowHeight + prevMinRowHeight;
                if(totalHeight < minHeight) {
                    mh[i][0] = totalHeight;
                    mh[i][1] = rowSpace;
                    minHeight = totalHeight;
                }
            }
        }

        int lastBook = books.length - 1;
        return mh[lastBook][0];
    }

    public static void main(String[] args) {
        Problem1105 sol = new Problem1105();
        int ret = sol.minHeightShelves(InputUtil.parseToNestedArrays("[[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]]"), 4);
        System.out.println(ret);
    }

}
