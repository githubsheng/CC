package indeed;

import java.util.*;

public final class WhatCommit {

    public List<String> printAllNode(Node node) {

        List<String> r = new ArrayList<>(); //result
        Queue<Node> q = new LinkedList<>(); //queue
        Set<Node> s = new HashSet<>(); //visited

        q.offer(node);

        while(!q.isEmpty()) {

            int size = q.size();
            StringBuilder sb = new StringBuilder();
            String d = ""; //delimiter

            for(int i = 0; i < size; i++) {

                Node n = q.poll();

                sb.append(d); //delimiter before first element is empty
                sb.append(n.id);
                d = " "; //before other elements, it is empty space

                s.add(n);
                for(Node p : n.parents) {
                    if(!s.contains(p)) q.offer(p);
                }

            }

            r.add(sb.toString());
        }

        return r;

    }

    public Node findFirstCommonParent(Node n1, Node n2) {

        if(n1 == null || n2 == null) throw new IllegalArgumentException("args cannot be null");

        Queue<Node> q1 = new LinkedList<>(); //queue
        Set<Node> s1 = new HashSet<>(); //visited

        Queue<Node> q2 = new LinkedList<>();
        Set<Node> s2 = new HashSet<>();

        q1.offer(n1);
        q2.offer(n2);

        while(!q1.isEmpty() && !q2.isEmpty()) {
            Node r = null;
            r = helper(q1, s1, s2);
            if(r != null) return r;
            r = helper(q2, s2, s1);
            if(r != null) return r;
        }

        return null;

    }

    private Node helper(Queue<Node> q, Set<Node> s, Set<Node> c /*check*/) {
        int size = q.size();
        for(int i = 0; i < size; i++) {
            Node n = q.poll();
            //first time we reach a node, from a source node
            if(c.contains(n)) return n;
            s.add(n);
            for(Node p : n.parents) {
                if(!s.contains(p)) q.offer(p);
            }
        }
        return null;
    }

    //this is just an example.
    public static final class Node {

        public String id;
        public List<Node> parents = new ArrayList<>();

    }

}





