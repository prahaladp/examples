/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = null;
        ListNode s = null;
        ListNode c = null;
        
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        p = head;
        c = head;
        s = null;
        
        while (c != null) {
            if (c.next == null || c.next.val != c.val) {
                // add here
                if (s == null) {
                    s = c;    
                    c = c.next;
                    s.next = null;
                    p = s;
                } else {
                    p.next = c;
                    p = c;
                    c = c.next;
                    p.next = null;
                }
                continue;
            }
            Integer pval = c.val;
            while (c != null && c.val == pval) {
                c = c.next;
            }
        }
        return s;
    }
}
