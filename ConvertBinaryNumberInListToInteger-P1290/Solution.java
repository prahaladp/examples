class Solution {
    public int getDecimalValue(ListNode head) {
        int sum = 0;
        for(ListNode n = head; n != null; n = n.next) {
            sum <<= 1;
            sum += n.val;
        }
        return sum;
    }
}
