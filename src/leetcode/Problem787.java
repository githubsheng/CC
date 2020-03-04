package leetcode;

import references.input.InputUtil;

import java.util.*;

public class Problem787 {

        public int findCheapestPrice ( int n, int[][] flights, int src, int dst, int k){
            Map<Integer, List<Flight>> graph = createGraph(flights);
            Scout srcScout = new Scout();
            srcScout.city = src;
            Queue<Scout> q = new LinkedList<>();
            q.offer(srcScout);
            int minCostToDst = -1;
            int stops = -1; //neighbours of src, although uses 1 flight, counted as 0 stop according to description

            while (!q.isEmpty()) {
                if (stops > k) return minCostToDst;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    Scout s = q.poll();
                    int city = s.city;
                    if (city == dst && (minCostToDst == -1 || minCostToDst > s.cost)) minCostToDst = s.cost;
                    if (!graph.containsKey(city)) continue;
                    for (Flight f : graph.get(city)) {
                        Scout ns = new Scout();
                        ns.cost = s.cost + f.cost;
                        ns.city = f.toCity;
                        if (minCostToDst != -1 && ns.cost > minCostToDst) continue;
                        q.add(ns);
                    }
                }
                stops++;
            }
            return minCostToDst;
        }


        Map<Integer, List<Flight>> createGraph ( int[][] flights){
            Map<Integer, List<Flight>> graph = new HashMap<>();
            for (int[] f : flights) {
                int from = f[0];
                int to = f[1];
                int cost = f[2];
                if (!graph.containsKey(from)) graph.put(from, new ArrayList<Flight>());
                graph.get(from).add(new Flight(to, cost));
            }
            return graph;
        }


        private static class Scout implements Comparable<Scout> {
            int cost;
            int city;

            @Override
            public int compareTo(Scout other) {
                return cost - other.cost;
            }
        }

        private static class Flight {
            int toCity;
            int cost;

            Flight(int toCity, int cost) {
                this.toCity = toCity;
                this.cost = cost;
            }
        }





        public static void main(String[] args) {
        Problem787 sol = new Problem787();
        sol.findCheapestPrice(3,
                InputUtil.parseToNestedArrays("[[0,1,100],[1,2,100],[0,2,500]]"),
        0,
        2,
        1);
    }


}
