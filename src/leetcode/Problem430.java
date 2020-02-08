package leetcode;

import java.util.LinkedList;

public class Problem430 {

    public Node flatten(Node head) {
        LinkedList<Node> stack = new LinkedList<>();

        Node end = head;

        while(true) {
            if(end.child != null) {
                //store end's next
                if(end.next != null) stack.push(end.next);
                Node child = end.child;
                end.child = null;
                end.next = child;
                child.prev = end;
                end = child;
            } else if (end.next != null){
                //child is null
                end = end.next;
            } else {
                //end.next == null && end.child == null
                //we reach an end.
                if (!stack.isEmpty()) {
                    Node returnNode = stack.pop();
                    end.next = returnNode;
                    returnNode.prev = end;
                } else {
                    break;
                }
            }

        }
        return head;
    }


    public static void main(String[] args) {
        Node p1 = new Node();
        Node p2 = new Node();
        Node p3 = new Node();
        Node p4 = new Node();
        p1.val = 1;
        p2.val = 2;
        p3.val = 3;
        p4.val = 4;
        p1.next = p2;
        p2.prev = p1;
        p2.child = p3;
        p3.prev = p2;
        p2.next = p4;
        p4.prev = p2;

        Problem430 sol = new Problem430();
        sol.flatten(p1);
        System.out.println(1);
    }


    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    };
}
