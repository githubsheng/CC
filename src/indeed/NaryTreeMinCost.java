package indeed;

import java.util.*;

public class NaryTreeMinCost {

    public static void main(String[] args) {
        NaryTreeMinCost sol = new NaryTreeMinCost();

        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.children.add(n2);
        root.children.add(n3);

        TreeNode n21 = new TreeNode(1);
        n2.children.add(n21);
        n21.children.add(new TreeNode(1));

        n3.children.add(new TreeNode(99));

        List<TreeNode> ret = sol.findMinCostFromRootToLeafUsingDfs(root);
        for(TreeNode n : ret) {
            System.out.println(n.cost);
        }

        System.out.println("-----");

        ret = sol.findMinCostUsingBfs(root);
        for(TreeNode n : ret) {
            System.out.println(n.cost);
        }
    }

    private static class TreeNode {
        int cost;
        List<TreeNode> children = new ArrayList<>();
        TreeNode(int _cost) {
            cost = _cost;
        }
    }

    //graph node
    private static class Node {
        int cost;
    }



    public List<TreeNode> findMinCostFromRootToLeafUsingDfs(TreeNode root) {
        if(root == null) return Collections.emptyList();
        Path currentPath = new Path();
        Path result = new Path();
        result.totalCost = Integer.MAX_VALUE;
        helper(root, currentPath, result);
        return result.path;
    }

    private void helper(TreeNode node, Path path, Path result) {
        path.addNode(node);
        //if node is leaf
        if(isLeaf(node) && path.totalCost < result.totalCost) {
            result.copyFromPath(path);
        } else {
            for(TreeNode c : node.children) {
                helper(c, path, result);
            }
        }
        path.backTrack();
    }

    private boolean isLeaf(TreeNode node) {
       return  node.children == null || node.children.isEmpty();
    }

    private static class Path {
        List<TreeNode> path = new ArrayList<>();
        int totalCost;

        void addNode(TreeNode node) {
            path.add(node);
            totalCost += node.cost;
        }

        void copyFromPath(Path other) {
            path = new ArrayList<>(other.path);
            totalCost = other.totalCost;
        }

        void backTrack() {
            TreeNode last = path.remove(path.size() - 1);
            totalCost -= last.cost;
        }
    }


    private static class PathNode {
        int totalCost;
        PathNode prevInPath;
        TreeNode treeNode;

        PathNode(PathNode prev, TreeNode treeNode) {
            this.prevInPath = prev;
            this.treeNode = treeNode;
            this.totalCost = treeNode.cost;
            if(prev != null) this.totalCost += prev.totalCost;
        }

        List<TreeNode> getPathFromRoot() {
            List<TreeNode> list = new ArrayList<>();
            PathNode pn = this;
            while(pn != null) {
                list.add(pn.treeNode);
                pn = pn.prevInPath;
            }
            Collections.reverse(list);
            return list;
        }


        boolean isLeaf() {
            return treeNode.children.isEmpty();
        }

    }

    public List<TreeNode> findMinCostUsingBfs(TreeNode root) {
        PathNode minLeaf = new PathNode(null, null);
        minLeaf.totalCost = Integer.MAX_VALUE;

        Queue<PathNode> q = new LinkedList<>();
        q.offer(new PathNode(null, root));
        while(!q.isEmpty()) {
            PathNode pn = q.poll();

            if(pn.totalCost >= minLeaf.totalCost) continue;

            if(!pn.isLeaf()) {
                for(TreeNode tn : pn.treeNode.children) q.offer(new PathNode(pn, tn));
            } else {
                minLeaf = pn;
            }
        }
        return minLeaf.getPathFromRoot();
    }


    /*
        E: number of nodes, V: number of edges
        ( E + V ) * lg(V)
        note: every edge results in an queue.offer operation, every node results in a queue.poll operation
     */
    public List<Node> findMinCostPathInGraph(Node start, Map<Node, LinkedList<Node>> graph) {

        Map<Node, Integer> minCost = new HashMap<>();

        for(Node node : graph.keySet()) minCost.put(node, Integer.MAX_VALUE);

        Set<Node> v = new HashSet<>(); //visited, or processed
        Map<Node, Node> mp = new HashMap<>(); // node -> prev node in a min path
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> minCost.get(a) - minCost.get(b));

        int minLeafCost = Integer.MAX_VALUE;
        Node minLeaf = null;

        pq.offer(start);
        minCost.put(start, start.cost);
        v.add(start);

        while(!pq.isEmpty()) {

            Node n = pq.poll();
            if(v.contains(n)) continue;
            v.add(n);
            int currCost = minCost.get(n);
            if(currCost >= minLeafCost) continue;

            if(graph.get(n).isEmpty() && currCost < minLeafCost) {
                minLeafCost = currCost;
                minLeaf = n;
            }



            for(Node next : graph.get(n)) {
                //if a node does not lead to anywhere, graph.get(n) should return empty collection rather than null

                int cost = next.cost + currCost;
                if(cost < minCost.get(next) && cost < minLeafCost) {
                    minCost.put(next, cost);
                    pq.add(next);
                    mp.put(next, n);
                }

            }
        }

        List<Node> ret = new ArrayList<>();
        Node n = minLeaf;

        while(n != null) {
            ret.add(n);
            n = mp.get(n); //for start, this will be null
        }

        Collections.reverse(ret);

        return ret;
    }


}


