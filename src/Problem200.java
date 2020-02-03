public class Problem200 {
    boolean[][] visited;
    char[][] grid;
    int rL; //row length
    int cL; //column length
    int count;
    int[] left = new int[]{0, -1};
    int[] right = new int[]{0, 1};
    int[] top = new int[]{-1, 0};
    int[] down = new int[]{1, 0};
    int[][] surroundings = new int[][]{left, right, top, down};

    public int numIslands(char[][] grid) {

        this.grid = grid;
        rL = grid.length;
        if(rL == 0) return 0;
        cL = grid[0].length;
        if (cL == 0) return 0;
        visited = new boolean[rL][cL];

        for(int i = 0; i < rL; i++) {
            for(int j = 0; j < cL; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs(i, j);
                }
            }
        }

        return count;

    }

    private void dfs(int rowIdx, int colIdx) {
        visited[rowIdx][colIdx] = true;
        for(int[] dir : surroundings) {
            int r = rowIdx + dir[0];
            int c = colIdx + dir[1];
            if(r >= 0 && c >=0 && r < rL && c < cL && !visited[r][c] && grid[r][c] == '1') {
                dfs(r, c);
            }
        }
    }

    public static void main(String[] args) {
        char[] c1 = new char[]{'1', '1', '0', '0', '0'};
        char[] c2 = new char[]{'1', '1', '0', '0', '0'};
        char[] c3 = new char[]{'0', '0', '1', '0', '0'};
        char[] c4 = new char[]{'0', '0', '0', '1', '1'};
        char[][] grid = new char[][]{c1, c2, c3, c4};
        Problem200 sol = new Problem200();
        int ret = sol.numIslands(grid);
        System.out.println(ret);
    }

}
