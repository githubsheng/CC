package indeed;

public final class UnrolledLinkedList {

    private int size;
    private Node head;
    private int numberOfNodes;

    public UnrolledLinkedList(int nodeSize, float factor){
        head = new Node(nodeSize, factor);
        numberOfNodes = 1;
    }

    public int get(int index) {
        if(index >= size) throw new IllegalArgumentException("index out of bound");
        NodeOffset no = getInsertionNode(head, index);
        if(no.offset == no.node.size) no = new NodeOffset(no.node.next, 0);
        return no.node.get(no.offset);
    }

    public void add(int val) {
        insert(val, size);
    }

    public void insert(int val, int index) {
        if(index > size) throw new IllegalArgumentException("index out of bound");
        NodeOffset no = getInsertionNode(head, index);
        Node node = no.node;
        int offset = no.offset;
        if(node.isFull()) {
            node.split();
            numberOfNodes++;
            no = getInsertionNode(node, offset);
            node = no.node;
            offset = no.offset;
        }
        node.add(val, offset);
        size++;
        tryCollectWastedSpace();
    }

    private NodeOffset getInsertionNode(Node start, int index) {
        if(index == 0) return new NodeOffset(start, 0);
        Node node = start;
        int offset = index;
        while(offset - node.size > 0) {
            offset -= node.size;
            node = node.next;
        }
        return new NodeOffset(node, offset);
    }

    private void tryCollectWastedSpace() {
        if(numberOfNodes * head.fixedSize > size * 1.5f) {
            //todo: make a new copy, with no empty spaces.
        }
    }

    public void printNodes() {
        Node node = head;
        while(node != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Node: ").append(" | ");
            for(int i = 0; i < node.size; i++) {
                sb.append(' ').append(node.arr[i]);
            }
            System.out.println(sb);
            node = node.next;
        }
    }

    private static final class Node {
        int[] arr;
        int fixedSize;
        int size;
        float factor;
        Node next;

        Node(int fixedSize, float factor) {
            this.fixedSize = fixedSize;
            this.arr = new int[fixedSize];
            //validation here for factor.
            this.factor = factor;
        }

        int get(int idx) {
            return arr[idx];
        }

        void add(int val, int idx) {
            if(idx == size) {
                arr[size] = val;
            } else {
                int elementsMoved = size - idx;
                System.arraycopy(arr, idx, arr, idx + 1, elementsMoved);
                arr[idx] = val;
            }
            size++;
        }

        //split the current node into two, and maintain next pointer
        void split(){
            Node newNode = new Node(fixedSize, factor);
            if(next == null) {
                next = newNode;
            } else {
                Node temp = next;
                next = newNode;
                newNode.next = temp;
            }

            //copy some of existing values to new node
            int start = (int)(fixedSize * factor);
            int elemsMoved = size - start;
            System.arraycopy(arr, start, newNode.arr, 0, elemsMoved);
            size -= elemsMoved;
            newNode.size = elemsMoved;
        }


        boolean isFull(){
            return arr.length == size;
        }

    }

    private static final class NodeOffset {
        Node node;
        int offset;

        NodeOffset(Node node, int offset) {
            this.node = node;
            this.offset = offset;
        }
    }

    public static void main(String[] args) {
        UnrolledLinkedList sol = new UnrolledLinkedList(6, 0.5f);
        for (int i = 0; i < 7; i++) {
            sol.add(i);
            sol.printNodes();
            System.out.println("------");
        }
        sol.insert(3, 3);
        sol.printNodes();
        System.out.println("------");


        sol.insert(2, 2);
        sol.printNodes();
        System.out.println("------");

        System.out.println("total in sequence");
        for (int i = 0; i < sol.size; i++) {
            System.out.print(" " + sol.get(i));
        }

    }


}
