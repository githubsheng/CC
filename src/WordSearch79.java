//solved this issue using 23 min

public class WordSearch79 {


    private char[][] board;
    private boolean[][] taken;
    private int width;
    private int height;
    private int lastWordIdx;

    public boolean exist(char[][] board, String word) {

        this.board = board;
        width = board.length;
        height = board[0].length;
        lastWordIdx = word.length() - 1;

        this.taken = new boolean[width][height];

        char first = word.charAt(0);

        for(int i = 0; i < width; i++) {

            for(int j = 0; j < height; j++) {

                if(first == board[i][j]) {

                    //dfs with backtracking
                    boolean ret = dfs(i, j, word, 0);
                    if(ret) return true;
                    continue;
                }

            }

        }



        return false;
    }

    public boolean dfs(int i, int j, String word, int wordIdx) {
        if(taken[i][j]) return false;

        char c = board[i][j];
        char c2 = word.charAt(wordIdx);
        if(c2 != c) return false;

        if(c2 == c && wordIdx == lastWordIdx) return true;

        taken[i][j] = true;

        int _wordIdx = wordIdx + 1;
        boolean ret;
        //go up
        if(j > 0) {
            ret = dfs(i, j - 1, word, _wordIdx);
            if(ret) return true;
        }

        //go down
        if(j + 1 < height) {
            ret = dfs(i, j + 1, word, _wordIdx);
            if(ret) return true;
        }

        //go left
        if(i > 0) {
            ret = dfs(i - 1, j, word, _wordIdx);
            if(ret) return true;
        }

        if(i + 1 < width) {
            ret = dfs(i + 1, j, word, _wordIdx);
            if(ret) return true;
        }

        taken[i][j] = false;

        return false;
    }

    public static void main(String[] args) {
        WordSearch79 sol = new WordSearch79();

        char[] row1 = new char[]{'A','B','C','E'};
        char[] row2 = new char[]{'S','F','C','S'};
        char[] row3 = new char[]{'A','D','E','E'};
        char[][] board = new char[][]{row1, row2, row3};

        boolean ret = sol.exist(board, "ABCB");
        System.out.println(ret);
    }

}
