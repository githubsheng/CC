package leetcode;

public class Problem206 {

     public class ListNode {
         int val;
         ListNode next;

         ListNode(int x) {
             val = x;
         }
     }

    public ListNode reverseList(ListNode head) {
        ListNode p1 = head;
        if(head == null) return head;
        ListNode p2 = head.next;
        if(p2 == null) return p1;
        ListNode p3 = p2.next;
        head.next = null;
        while(p3 != null) {
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            p3 = p3.next;
        }
        p2.next = p1;
        return p2;
    }


    public static void main(String[] args) {

    }

}
