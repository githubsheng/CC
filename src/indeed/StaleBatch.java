package indeed;

import java.util.*;

class StaleBatch {

    public Set<String> findStaleBatches(String[][] preSteps, String[][] lastExecutionTimes) {

        Map<String, Integer> indegree = new HashMap<>();
        Set<String> stale = new HashSet<>();
        Map<String, List<String>> graph = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> last = new HashMap<>();

        preProcess(preSteps, graph, indegree);
        executeTimeHelper(last, lastExecutionTimes);

        for(Map.Entry<String, Integer> entry : indegree.entrySet()) {
            if(entry.getValue() == 0) queue.offer(entry.getKey());
        }

        while(!queue.isEmpty()) {
            String batch = queue.poll();
            for(String c : graph.get(batch)) {

                if(last.get(batch).compareTo(last.get(c)) >= 0 //batch ran after its children, means, children not yet reran
                        || stale.contains(batch)) { //or batch itself is already identified as stale
                    stale.add(c); //its children must be stale too.
                }

                int cin = indegree.get(c) - 1;
                indegree.put(c, cin);
                if(cin == 0) {
                    queue.offer(c);
                }
            }
        }

        return stale;
    }


    private void preProcess(String[][] preSteps,
                            Map<String, List<String>> graph,
                            Map<String, Integer> indegree) {
        for(String[] conn : preSteps) {

            String p = conn[0];
            String c = conn[1];

            //build indegree
            //if we haven't seen parent, put parent as well, its indegree is 0
            if(!indegree.containsKey(p)) indegree.put(p, 0);
            //setting up for children
            indegree.put(c, indegree.getOrDefault(c, 0) + 1);

            //build adjacent list as graph.
            if(!graph.containsKey(p)) {
                List<String> cs = new ArrayList<>();
                cs.add(c);
                graph.put(p, cs);
            } else {
                graph.get(p).add(c);
            }
        }
    }

    private void executeTimeHelper(Map<String, String> last, String[][] lastExecutionTimes) {
        for(String[] l : lastExecutionTimes) {
            last.put(l[0], l[1]);
        }
    }


}