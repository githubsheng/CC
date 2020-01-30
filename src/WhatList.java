import java.util.Arrays;

public final class WhatList {
    private Node head;
    private int totalLength;
    private int totalNodes;
    private int nodeSize;

    public WhatList(int nodeSize) {
        head = new Node(nodeSize);
        this.nodeSize = nodeSize;
    }

    public int get(int index) {
        NodeOffset t = getNode(head, index);
        return t.node.getVal(t.idxInNode);
    }

    public void add(int val) {
        Node lastNode = getLastNode();
        if (lastNode.isFull()) {
            lastNode.split();
            totalNodes++;
            lastNode = lastNode.next;
        }
        lastNode.insert(val, lastNode.length);
        totalLength++;
        tryConsolidate();
    }

    public void insert(int val, int index) {
        NodeOffset t = getNode(head, index);
        Node insertionNode = t.node;
        int idxInNode = t.idxInNode;
        if(insertionNode.isFull()) {
            insertionNode.split();
            totalNodes++;
        }
        //now we need to decide which of these split to insert...
        //if idx is out of bound of original node, then its in second node.
        t = getNode(insertionNode, idxInNode);
        t.node.insert(val, t.idxInNode);
        totalLength++;
        tryConsolidate();
    }

    public void delete(int index) {
        NodeOffset t = getNode(head, index);
        t.node.delete(t.idxInNode);
        if(t.node.length == 0) {
            t.node.prev.next = t.node.next;
            totalNodes--;
        }
        totalLength--;
        tryConsolidate();
    }

    private void tryConsolidate(){
        if((double)totalLength / totalNodes * nodeSize < 0.66) {
            //todo: create another list, and copy all elements to it, leaving no empty space.
            //todo: this is a lot of code so let me skip it for now...
        }
    }

    private NodeOffset getNode(Node head, int index) {
        Node node = head;
        while(node.next != null && index - node.length >= 0) {
            index -= node.length;
            node = node.next;
        }
        return new NodeOffset(node, index);
    }

    private Node getLastNode() {
        //make index > totalLength so getNode will stops at very last node.
       return getNode(head, totalLength + 1).node;
    }

    public void print(){
        System.out.println("---------");
        Node node = head;
        while(node != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("node count:");
            sb.append(node.length);
            sb.append(" node content:");
            for(int i = 0; i < node.length; i++) {
                sb.append(' ');
                sb.append(node.arr[i]);
            }
            System.out.println(sb.toString());
            node = node.next;
        }
    }

    private int size(){
        return totalLength;
    }

    private static final class Node {
        int[] arr;
        int length;
        Node next;
        Node prev;

        Node(int fixedSize) {
            this.arr = new int[fixedSize];
        }

        int getVal(int index) {
            return arr[index];
        }

        void insert(int val, int index) {
            if (index == length + 1) {
                arr[index] = val;
            } else {
                System.arraycopy(arr, index, arr, index + 1, length - index);
                arr[index] = val;
            }
            length++;
        }

        void delete(int index) {
            int elementsMoved = length - index - 1;
            System.arraycopy(arr, index + 1, arr, index, elementsMoved);
            length--;
        }

        //split the current node into two, and maintain next pointer
        void split() {
            Node newNode = new Node(this.arr.length);
            newNode.prev = this;
            if (next == null) {
                next = newNode;
            } else {
                Node tmp = this.next;
                this.next = newNode;
                newNode.next = tmp;
                tmp.prev = newNode;
            }

            //copy half of existing values to new node, starting from start (inclusive)
            int start = length / 2;
            int length = this.length - start; //last idx - start idx + 1 = count - 1 - (count/2) + 1
            System.arraycopy(arr, start, newNode.arr, 0, length);
            this.length = start;
            newNode.length = length;
        }

        boolean isFull() {
            return arr.length == length;
        }

    }

    private static final class NodeOffset {
        Node node;
        int idxInNode;

        NodeOffset(Node node, int idxInNode) {
            this.node = node;
            this.idxInNode = idxInNode;
        }
    }

    public static void main(String[] args) {
        WhatList list = new WhatList(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.print();
        list.add(6);
        list.add(7);
        list.print();

        list.insert(100, 2);
        list.insert(99, 2);
        list.insert(98, 2);
        list.print();

        list.delete(5);
        list.print();
        list.delete(5);
        list.print();

        System.out.println("-----");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}