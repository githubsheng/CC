package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem1162 {

    private Queue<int[]> queue = new LinkedList<>();
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxDistance(int[][] grid) {
        if(grid.length == 0) return -1;

        int max = -1;
        int colCount = grid[0].length;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < colCount; j++) {
                int cell = grid[i][j];
                if(cell == 0) {
                    int distance = findNearestLand(grid, i, j);
                    if(distance == -1) return -1;
                    max = Math.max(max, distance);
                }
            }
        }
        return max;
    }

    private int findNearestLand(int[][] grid, int i, int j) {
        queue.clear();
        queue.offer(new int[]{i, j});
        int rs = grid.length;
        int cs = grid[0].length;
        int[][] visited = new int[rs][cs];
        while(!queue.isEmpty()) {
            int[] cell = queue.poll();
            int i2 = cell[0];
            int j2 = cell[1];
            visited[i2][j2] = 1;
            for(int[] dir : dirs) {
                int ni = i2 + dir[0];
                int nj = j2 + dir[1];
                if(ni < 0 || ni >= rs) continue;
                if(nj < 0 || nj >= cs) continue;
                if(visited[ni][nj] == 1) continue;
                if(grid[ni][nj] == 1) return Math.abs(i - ni) + Math.abs(j - nj);
                queue.offer(new int[]{ni, nj});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0, 0},{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        Problem1162 sol = new Problem1162();
        int ret = sol.maxDistance(grid);
    }

}
