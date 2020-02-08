package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem23 {
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists.length == 0) return null;
            if(lists.length == 1) return lists[0];

            PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (o1, o2) -> Integer.compare(o1.val, o2.val));

            minHeap.addAll(Arrays.asList(lists));

            ListNode dummy = new ListNode(-1);
            ListNode head = dummy;
            while(minHeap.size() != 1) {
                ListNode node = minHeap.poll();
                head.next = node;
                if(node.next != null) {
                    minHeap.add(node.next);
                }
                head = node;
            }

            head.next = minHeap.poll();
            return dummy.next;
        }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}


