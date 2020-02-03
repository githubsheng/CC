//0:37 ~ 0:45  8min
public class Problem21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        //dummy
        ListNode head = new ListNode(-1);
        ListNode node = head;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                node.next = l1;
                node = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                node = l2;
                l2 = l2.next;
            }
        }

        if(l1 != null) {
            node.next = l1;
        }

        if(l2 != null) {
            node.next = l2;
        }

        return head.next;
    }





     private static  class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
     }

}
