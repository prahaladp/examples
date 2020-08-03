class Solution {
    public int[] nextLargerNodes(ListNode head) {
        int sz = 0;
        ListNode n = head;
        
        while (n != null) {
            sz++;
            n = n.next;
        }
        
        if (sz == 0) {
            return null;
        }
        
        int[] res = new int[sz];
        int ind = 0;
        for (n = head; n != null; n = n.next) {
            int mx = Integer.MAX_VALUE;
            for (ListNode j = n.next; j != null; j = j.next) {
                if (j.val > n.val && j.val < mx) {
                    mx = j.val;
                    break;
                }
            }
            
            if (mx == Integer.MAX_VALUE) {
                mx = 0;
            }
            res[ind++] = mx;
        }
        
        return res;
    }
}
