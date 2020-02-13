import java.io.*;
import java.util.*;

public class Solution {

    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
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

        int targetColorCounts = 0;
        for(int i = 0; i < colors.length; i++) {
            if(colors[i] == val) targetColorCounts++;
        }

        if(targetColorCounts < 2) return -1;

        for(int i = 0; i < colors.length; i++) {
            if(colors[i] == val) {
                int d = shortestFrom(i + 1, g, colors, val);
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
                if(n != from && colors[n-1] == targetColor) return d;
                for(Integer adj : graph.get(n)) {
                    q.offer(adj);
                }
                v.add(n);
            }
            d++;
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        Scanner scanner = new Scanner(Solution.class.getResourceAsStream("1.txt/input.txt"));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        System.out.println(ans);
//        bufferedWriter.write(String.valueOf(ans));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
