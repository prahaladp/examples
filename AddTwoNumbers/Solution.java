/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r;
        ListNode head = null;
        ListNode prev = null;
        int carryover = 0;
        int sum = 0;
        
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carryover;
            carryover = 0;
            if (sum >= 10) {
                sum -= 10;
                carryover = 1;
            }
            r = new ListNode(sum);
            if (head == null) {
                head = r;
                prev = head;
            } else {
                prev.next = r;
                prev = r;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        
        ListNode rem = l1;
        if (rem == null) {
            rem = l2;
        }
        while (rem != null) {
            sum = rem.val + carryover;
            carryover = 0;
            if (sum >= 10) {
                sum -= 10;
                carryover = 1;
            }
            r = new ListNode(sum);
            if (head == null) {
                head = r;
                prev = head;
            } else {
                prev.next = r;
                prev = r;
            }
            rem = rem.next;
        }
        if (carryover != 0) {
            r = new ListNode(carryover);
            if (head == null) {
                head = r;
                prev = head;
            } else {
                prev.next = r;
                prev = r;
            }
        }
        
        rem = head;
        sum = 0;
        while (rem != null) {
            sum = (sum * 10) + (rem.val);
            rem = rem.next;
        }
        
        return head;
   }
}
