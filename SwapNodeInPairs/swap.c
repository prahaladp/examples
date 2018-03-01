/**
 * Definition for singly-linked list.
 */

#include <stdio.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

void printList(struct ListNode *head) {
    struct ListNode *curr = head;
    
    while (curr) {
        printf("-> %d ", curr->val);
        curr = curr->next;
    }
    
    printf("\n");
}

struct ListNode* swapPairs(struct ListNode* head) {
    struct ListNode *prev = NULL;
    struct ListNode *curr = head;
    
    while (curr) {
        if (curr->next) {
            if (prev) {
                prev->next = curr->next;
                curr->next = curr->next->next;
                prev->next->next = curr;
                
                prev = prev->next->next;
                curr = curr->next;
                
            } else {
                
                /* beginning of the list */
                head = curr->next;
                curr->next = head->next;
                head->next = curr;
                
                prev = curr;
                curr = curr->next;
            }
        } else {
            break;
        }
    }
    return head;
}

int main(int argc, char **argv) {
    struct ListNode arr[10];
    struct ListNode *head = NULL;
    
    arr[0].val = 0;
    arr[1].val = 1;
    arr[2].val = 2;
    arr[3].val = 3;
    arr[4].val = 4;
    
    arr[0].next = &arr[1];
    arr[1].next = &arr[2];
    arr[2].next = &arr[3];
    arr[3].next = &arr[4];
    arr[4].next = NULL;
    arr[5].next = NULL;
    
    head = swapPairs(&arr[0]);
    printList(head);
    
    arr[0].val = 0;
    arr[0].next = NULL;
    head = swapPairs(&arr[0]);
    printList(head);
    
    arr[0].val = 0;
    arr[1].val = 1;
    arr[2].val = 2;
    arr[3].val = 3;
    arr[4].val = 4;
    
    arr[0].next = &arr[1];
    arr[1].next = &arr[2];
    arr[2].next = &arr[3];
    arr[3].next = NULL;
    arr[4].next = NULL;
    arr[5].next = NULL;
    
    head = swapPairs(&arr[0]);
    printList(head);

    arr[0].val = 0;
    arr[1].val = 1;
    arr[2].val = 2;
    arr[3].val = 3;
    arr[4].val = 4;
    
    arr[0].next = &arr[1];
    arr[1].next = &arr[2];
    arr[2].next = NULL;
    arr[3].next = NULL;
    arr[4].next = NULL;
    arr[5].next = NULL;
    
    head = swapPairs(&arr[0]);
    printList(head);

};
