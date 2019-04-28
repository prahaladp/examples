/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* reverseBetween(struct ListNode* head, int m, int n) {
    struct ListNode *sprev = NULL;
    struct ListNode *prev = NULL;
    struct ListNode *st = head;
    struct ListNode *spst = NULL;
    
    if (m==n) {
        return head;
    }
    
    int ind = 0;
    
    for (;ind < n ; ind++) {
        if (ind == m - 1) {
            // start reversing from here
            spst = st;
            sprev = prev;
            prev = st;
            st = st->next;
        } else if (ind == n - 1) {
            if (sprev == NULL) {
                head = st;
            } else {
                sprev->next = st;
            }
            spst->next = st->next;
            st->next = prev;
            return head;
        } else if (ind < m-1) {
            prev = st;
            st = st->next;
        } else {
            struct ListNode *tmp = st->next;
            st->next = prev;
            prev = st;
            st = tmp;
        }
    }
    return head;
}
