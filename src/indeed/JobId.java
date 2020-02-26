package indeed;

public class JobId {

    private Node root;

    public boolean isExpired(long value) {
        if(root == null) return false;
        Node c = root;
        while(c != null) {
            if(value > c.max) {
                c = c.right;
            } else if (value < c.min) {
                c = c.left;
            } else {
                return true;
            }
        }
        return false;
    }

    public void expire(long value) {
        if(root == null) {
            root = new Node(value);
            return;
        }
        Node c = root; //current
        while(c != null) {
            if(value <= c.min && value >= c.max) return; //id already in set.
            if(value == c.min - 1 ) {
                c.min = value;
                //find the previous interval in sorted order
                Node prev = c.left;
                if(prev == null) return;
                Node prevParent = c;
                while(prev.right != null) {
                    prevParent = prev;
                    prev = prev.right;
                }
                if(prev.max == c.min) {
                    //merge prev into c, so we can remove pre now.
                    prevParent.right = null;
                }
            } else if (value == c.max + 1) {
                c.max = value;
                //find the next interval in sorted order
                Node next = c.right;
                if(next == null) return;
                Node nextParent = c;
                while(next.left != null) {
                    nextParent = next;
                    next = next.left;
                }
                if(next.min == c.max) {
                    nextParent.left = null;
                }
            } else if( value < c.min) {
                if(c.left == null) {
                    c = c.left;
                } else {
                    c.left = new Node(value);
                }
            } else {
                if(c.right == null) {
                    c = c.right;
                } else {
                    c.right = new Node(value);
                }
            }
        }
    }

    private static class Node {
        long min;
        long max;
        Node left;
        Node right;
        Node(long init) {
            min = init;
            max = init;
        }
    }

}
