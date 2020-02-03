import java.util.*;

public class Problem133 {

//    public Node cloneGraph(Node node) {
//
//        if(node == null) return null;
//
//        Deque<Node> q = new LinkedList<>();
//        Set<Node> v = new HashSet<>();
//        Map<Node, Node> m = new HashMap<>();
//
//        cloneSingle(node, m);
//        q.offer(node);
//
//        while(!q.isEmpty()) {
//
//            Node n = q.poll();
//
//            if(v.contains(n)) continue;
//
//            Node c = m.get(n);
//
//            copyNeighbors(n, c, m);
//
//            v.add(n);
//
//            for(Node nb : n.neighbors) {
//                q.offer(nb);
//            }
//
//        }
//
//        return m.get(node);
//
//    }
//
//    private Node cloneSingle(Node src, Map<Node, Node> mapping) {
//        Node copy = new Node();
//        copy.val = src.val;
//        copy.neighbors = new ArrayList<>(src.neighbors.size());
//        mapping.put(src, copy);
//        return copy;
//    }
//
//    private void copyNeighbors(Node src, Node copy, Map<Node, Node> mapping) {
//        for(Node nb : src.neighbors) {
//            Node nbc = mapping.get(nb);
//            if(nbc == null) nbc = cloneSingle(nb, mapping);
//            copy.neighbors.add(nbc);
//        }
//    }

    public Node cloneGraph(Node node) {
        if(node == null) return null;

        Queue<Node> q = new LinkedList<>();
        boolean[] v = new boolean[101];
        Node[] m = new Node[101];

        cloneSingle(node, m);
        q.offer(node);

        while(!q.isEmpty()) {

            Node n = q.poll();

            if(v[n.val]) continue;

            Node c = m[n.val];

            copyNeighbors(n, c, m);

            v[n.val] = true;

            for(Node nb : n.neighbors) {
                q.offer(nb);
            }

        }

        return m[node.val];

    }

    private Node cloneSingle(Node src, Node[] mapping) {
        Node copy = new Node();
        copy.val = src.val;
        copy.neighbors = new ArrayList<>(src.neighbors.size());
        mapping[src.val] = copy;
        return copy;
    }

    private void copyNeighbors(Node src, Node copy, Node[] mapping) {
        for(Node nb : src.neighbors) {
            Node nbc = mapping[nb.val];
            if(nbc == null) nbc = cloneSingle(nb, mapping);
            copy.neighbors.add(nbc);
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node();
        n1.val = 1;

        Node n2 = new Node();
        n2.val = 2;

        Node n3 = new Node();
        n3.val = 3;

        n1.neighbors.add(n2);
        n2.neighbors.add(n1);

        n2.neighbors.add(n3);
        n3.neighbors.add(n2);


        Problem133 p = new Problem133();
        Node c1 = p.cloneGraph(n1);
        System.out.println(c1.val);
    }



    private static class Node {
        int val;
        List<Node> neighbors = new ArrayList<>();

        Node() {}

        Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}
