package indeed;

import java.util.Arrays;

public final class WhatList {

    private int nodeSize;
    private Node head;

    public WhatList(int nodeSize){
        this.nodeSize = nodeSize;
    }

    public int get(int index) {
        NodeOffset no = getNode(index);
        Node node = no.node;
        int offset = no.offset;
        int idxInNode = index - offset;
        return node.getVal(idxInNode);
    }

    public void add(int val) {
        Node lastNode = getLastNode();
        if(lastNode.isFull()) {
            lastNode.split();
            lastNode = lastNode.next;
        }
        lastNode.setVal(val, lastNode.count);
    }

    public void insert(int val, int index) {
        NodeOffset no = getNode(index);
        Node node = no.node;
        int offset = no.offset;
        if(node.isFull()) node.split();
        if(index > offset + node.count) {
            node = node.next;
            offset = offset + node.count;
        }
        int idxInNode = index - offset;
        node.setVal(val, idxInNode);
    }

    private NodeOffset getNode(int index) {
        int lastIdx = 0;
        Node node = head;
        while(node != null) {
            int startIdx = lastIdx;
            int endIdx = startIdx + node.count;
            if(index >= startIdx && index <= endIdx) return new NodeOffset(node, startIdx);
            lastIdx = endIdx;
            node = node.next;
        }
        throw new RuntimeException("index out of bound");
    }

    private Node getLastNode(){
        Node node = head;
        Node last = node;
        while(node != null) {
            last = node;
            node = node.next;
        }
        return last;
    }


    private static final class Node {
        int[] arr;
        int fixedSize;
        int count;
        Node next;

        Node(int fixedSize) {
            this.fixedSize = fixedSize;
            this.arr = new int[fixedSize];
        }

        int getVal(int index) {
            return arr[index];
        }

        void setVal(int val, int index) {
            if(index == count + 1) {
                arr[index] = val;
            } else {
                int[] newArr = Arrays.copyOf(arr, fixedSize);
                System.arraycopy(arr, index, newArr, index + 1, count - index + 1);
                newArr[index] = val;
                this.arr = newArr;
            }
        }

        //split the current node into two, and maintain next pointer
        void split(){
            Node newNode = new Node(this.fixedSize);
            if(next == null) {
                next = newNode;
            } else {
                Node next = this.next;
                this.next = newNode;
                newNode.next = next;
            }

            //copy half of existing values to new node
            int start = count/2;
            int length = count - count/2;
            System.arraycopy(arr, start, newNode.arr, 0, length);
            this.count /= 2;
            newNode.count = length;
        }


        boolean isFull(){
            return arr.length == count;
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


}
