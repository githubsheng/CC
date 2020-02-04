package indeed;

import java.util.*;

/**
 *
 * you are given a list of n streams (here i just use arrayList to mock a stream)
 * each streams contains numbers.
 * numbers in each stream are sorted, ascending
 * find unique numbers that appear in  K streams.
 */
public class KFrequentNumber {

    public Set<Integer> findElementsInAllStreams(List<List<Integer>> streams, int k) {

        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        Counter frequency = new Counter();
        Set<Integer> res = new HashSet<>();

        for(List<Integer> str : streams) {
            Node n = new Node(str);
            minHeap.offer(n);
            int freq = frequency.inc(n.val);
            if(freq == k) res.add(n.val);
        }

        while(!minHeap.isEmpty()) {
            Node n = minHeap.poll();
            int currVal = n.val;
            frequency.dec(currVal);
            if(!n.hasNext()) continue;
            n.advance();
            int newVal = n.val;
            minHeap.offer(n);
            int newValFreq = frequency.inc(newVal);
            if(newValFreq == k) res.add(newVal);
        }

        return res;
    }

    private static class Counter {
        Map<Integer, Integer> m = new HashMap<>();

        int inc(int key) {
            int count = m.getOrDefault(key, 0) + 1;
            m.put(key, count);
            return count;
        }

        int dec(int key) {
            int count =  m.get(key) - 1;
            m.put(key, count);
            return count;
        }

        Integer getCount(int key) {
            return m.get(key);
        }
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
        List<Integer> l1 = Arrays.asList(1, 4, 5, 5, 8, 9);
        List<Integer> l2 = Arrays.asList(2, 3, 5, 6, 7, 8, 9, 11, 12);
        List<Integer> l3 = Arrays.asList(2, 2, 4, 5, 7, 8, 12);
        List<Integer> l4 = Arrays.asList(2, 5, 8, 12);
        //expected, 2, 5, 8
        KFrequentNumber sol = new KFrequentNumber();
        Set<Integer> ans = sol.findElementsInAllStreams(Arrays.asList(l1, l2, l3, l4), 3);
        for(int i : ans) {
            System.out.println(i);
        }
    }

}
