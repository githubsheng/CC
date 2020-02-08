package leetcode;

import references.input.InputUtil;

import java.util.*;

public class Problem1192 {

    private List<Integer>[] map;
    private List<List<Integer>> results = new ArrayList<>();
    private int[] times;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        times = new int[n];
        map = new ArrayList[n];
        Arrays.fill(times, -1);

        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();
        }

        for(List<Integer> connection : connections) {
            int from = connection.get(0);
            int to = connection.get(1);
            map[from].add(to);
            map[to].add(from);
        }

        int start = connections.get(0).get(0);
        dfs(start, 1, -1);
        return results;
    }

    private int dfs(int node, int time, int from) {
        time++;
        times[node] = time;
        List<Integer> nexts = map[node];
        int minReach = time;
        for(int next : nexts) {
            if(next == from) continue;
            int t = times[next];
            int reach = t == -1 ? dfs(next, time, node) : t;
            minReach = Math.min(reach, minReach);
            if(reach > time) {
                results.add(Arrays.asList(node, next));
            }
        }
        times[node] = minReach;
        return minReach;
    }

    public static void main(String[] args) {
        Problem1192 sol = new Problem1192();
        List<List<Integer>> rets = sol.criticalConnections(5, InputUtil.parseToNestedList("[[1,0],[2,0],[3,0],[4,1],[4,2],[4,0]]"));
        System.out.println(rets);
    }

}
