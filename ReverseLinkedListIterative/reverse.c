/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */


struct ListNode* reverseList(struct ListNode* head){
    struct ListNode *prev = NULL;
    
    while (head) {
        struct ListNode *nxt = head->next;
        head->next = prev;
        prev = head;
        head = nxt;
    }
    return prev;
}

