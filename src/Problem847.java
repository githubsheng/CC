import references.input.InputUtil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Problem847 {

    public int shortestPathLength(int[][] graph) {

        if(graph.length == 0 || graph.length == 1) return 0;

        LinkedList<Scout> timeCtrl = new LinkedList<>();
        Set<Scout> scouts = new HashSet<>();

        for (int i = 0; i < graph.length; i++) {
            Scout sc = new Scout(1 << i, i, 0);
            timeCtrl.addLast(sc);
            scouts.add(sc);
        }

        int completeArea = 0;
        for (int i = 0; i < graph.length; i++) {
            completeArea = completeArea | ( 1 << i);
        }

        while(!timeCtrl.isEmpty()) {
            Scout scout = timeCtrl.removeFirst();
            int[] choices = graph[scout.currentPosition];
            for (int newPos : choices) {
                int newArea = scout.exploredNodes | 1 << newPos;
                int newSteps = scout.steps + 1;
                if(newArea == completeArea) return newSteps;
                Scout sendout = new Scout(newArea, newPos, newSteps);
                if (scouts.contains(sendout)) continue;
                scouts.add(sendout);
                timeCtrl.addLast(sendout);
            }
        }

        //todo: better, throw an runtime exception..
        return -1;
    }

    private static class Scout {
        int exploredNodes;
        int currentPosition;
        int steps;

        Scout(int exploredNodes, int currentPosition, int steps) {
            this.exploredNodes = exploredNodes;
            this.currentPosition = currentPosition;
            this.steps = steps;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Scout scout = (Scout) o;

            if (exploredNodes != scout.exploredNodes) return false;
            return currentPosition == scout.currentPosition;
        }

        @Override
        public int hashCode() {
            int result = exploredNodes;
            result = 31 * result + currentPosition;
            return result;
        }
    }

    public static void main(String[] args) {
        Problem847 sol = new Problem847();
        int ret = sol.shortestPathLength(InputUtil.parseToNestedArrays("[[1],[0,2,4],[1,3,4],[2],[1,2]]"));
        System.out.println(ret);
    }

}
