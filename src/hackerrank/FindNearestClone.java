package hackerrank;

import java.util.*;

public class FindNearestClone {

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] colors, int val) {
        // solve here
        int edgesCount = graphFrom.length;

        Map<Integer, List<Integer>> g = new HashMap<>();

        for (int i = 0; i < edgesCount; i++) {
            int from = graphFrom[i];
            int to = graphTo[i];
            if(!g.containsKey(from)) g.put(from, new ArrayList<>());
            if(!g.containsKey(to)) g.put(to, new ArrayList<>());
            g.get(from).add(to);
            g.get(to).add(from);
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < colors.length; i++) {
            if(colors[i] == val) {
                int d = shortestFrom(i, g, colors, val);
                if(d == -1) continue;
                ans = Math.min(d, ans);
            }
        }

        return ans == Integer.MAX_VALUE? -1 : ans;
    }

    private static int shortestFrom(int from, Map<Integer, List<Integer>> graph, long[] colors, int targetColor) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> v = new HashSet<>();
        q.add(from);

        int d = 0; //distance
        while(!q.isEmpty()) {
            int s = q.size();
            for(int i = 0; i < s; i++) {
                int n = q.poll();
                if(v.contains(n)) continue;
                if(n != from && colors[n] == targetColor) return d;
                for(Integer adj : graph.get(n)) {
                    q.offer(adj);
                }
                v.add(n);
            }
            d++;
        }
        return -1;
    }

}
