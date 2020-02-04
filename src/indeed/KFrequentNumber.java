package indeed;

import java.util.*;

/**
 *
 * you are given a list of K streams (here i just use arrayList to mock a stream)
 * each streams contains numbers.
 * numbers in each stream are sorted, ascending
 * find unique numbers that appear in all K streams.
 */
public class KFrequentNumber {

    public Set<Integer> findElementsInAllStreams(List<List<Integer>> streams) {

        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        int max = Integer.MIN_VALUE; //max in heap.

        for(List<Integer> str : streams) {
            Node n = new Node(str);
            minHeap.offer(n);
            max = Math.max(max, n.val);
        }

        Set<Integer> res = new HashSet<>();

        while(true) {
            Node n = minHeap.poll();
            if(n.val == max) res.add(n.val);
            if(!n.hasNext()) break;
            n.advance();
            minHeap.offer(n);
            max = Math.max(max, n.val);
        }

        return res;
    }

    private static class Node implements Comparable<Node> {
        List<Integer> stream;
        int index = 0;
        int val;

        Node(List<Integer> _stream) {
            stream = _stream;
            val = stream.get(index);
        }

        boolean hasNext(){
            return index + 1 < stream.size();
        }

        void advance(){
            if(!hasNext()) throw new IllegalStateException("no next element");
            val = stream.get(++index);
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(val, o.val);
        }

    }

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 2, 4, 5, 5, 8, 9);
        List<Integer> l2 = Arrays.asList(2, 3, 5, 6, 7, 8, 9, 11, 12);
        List<Integer> l3 = Arrays.asList(2, 2, 4, 5, 7, 8, 12);
        //expected, 2, 5, 8
        KFrequentNumber sol = new KFrequentNumber();
        Set<Integer> ans = sol.findElementsInAllStreams(Arrays.asList(l1, l2, l3));
        for(int i : ans) {
            System.out.println(i);
        }
    }

}
