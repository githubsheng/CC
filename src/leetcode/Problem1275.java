package leetcode;

import references.input.InputUtil;

public class Problem1275 {

    public String tictactoe(int[][] moves) {

        char[][] grid = new char[3][3];

        for (int i = 0; i < moves.length; i++) {
            char mark = i % 2 == 0 ? 'X' : 'O';
            int r = moves[i][0];
            int c = moves[i][1];
            grid[r][c] = mark;

            if(i > 3) {
                int ret = checkGrid(grid);
                if(ret == 1) return "A";
                if(ret == 2) return "B";
            }
        }

        if(moves.length < 9) return "Pending";
        return "Draw";
    }

    private int checkGrid(char[][] grid) {

        boolean isAwin = false;
        boolean isBwin = false;

        for (int i = 0; i < 3; i++) {
           if(grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2]) {
               if(grid[i][0] == 'X') isAwin = true;
               if(grid[i][0] == 'O') isBwin = true;
           }
        }

        for (int i = 0; i < 3; i++) {
            if(grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i]) {
                if(grid[0][i] == 'X') isAwin = true;
                if(grid[0][i] == 'O') isBwin = true;
            }
        }

        if(grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
            if(grid[0][0] == 'X') isAwin = true;
            if(grid[0][0] == 'O') isBwin = true;
        }

        if(grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) {
            if(grid[0][2] == 'X') isAwin = true;
            if(grid[0][2] == 'O') isBwin = true;
        }

        if(isAwin) return 1;
        if(isBwin) return 2;
        return 3;
    }

    public static void main(String[] args) {
        Problem1275 sol = new Problem1275();
        String ret = sol.tictactoe(InputUtil.parseToNestedArrays("[[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]"));
        System.out.println(ret);
    }

}
