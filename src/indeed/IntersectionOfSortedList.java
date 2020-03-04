package indeed;

import java.util.*;

public class IntersectionOfSortedList {

    public List<Integer> getIntersection(List<List<Integer>> lists) {
        List<Integer> ret = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        PriorityQueue<Itr> minHeap = new PriorityQueue<>();

        for(List<Integer> list : lists) {
            if(list.isEmpty()) return Collections.emptyList();
            Itr itr = new Itr(list);
            minHeap.add(itr);
            if(itr.value > max) max = itr.value;
        }

        while(true) {

            if(minHeap.peek().value == max) ret.add(max);

            Itr min = minHeap.poll();
            int minVal = min.value;

            while(min.hasNext()) {
                if(min.advance() != minVal) break;
            }

            //min itr does not effectively advance: no following elements or all following elements are the same
            if(min.value == minVal) return ret;

            minHeap.add(min);
            max = Math.max(max, min.value);
        }

    }

    private static class Itr implements Comparable<Itr> {

        List<Integer> list;
        int index = 0;
        int value = 0;

        Itr(List<Integer> list) {
            this.list = list;
            value = list.get(index);
        }

        boolean hasNext(){
            return index + 1 < list.size();
        }

        int advance() {
            value = list.get(++index);
            return value;
        }

        @Override
        public int compareTo(Itr other) {
            return value - other.value;
        }

    }


    public static void main(String[] args) {
        IntersectionOfSortedList sol = new IntersectionOfSortedList();
        List<Integer> l1 = Arrays.asList(1, 4, 5, 5, 8, 9, 12);
        List<Integer> l2 = Arrays.asList(2, 3, 5, 6, 7, 8, 9, 11, 12);
        List<Integer> l3 = Arrays.asList(2, 2, 4, 5, 7, 8, 12);
        List<Integer> l4 = Arrays.asList(2, 5, 8, 11, 12, 12, 12, 12);
        List<Integer> ret = sol.getIntersection(Arrays.asList(l1, l2, l3, l4));
        System.out.println(ret);
    }





}
