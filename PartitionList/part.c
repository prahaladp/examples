#include<stdio.h>
#include<stdlib.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

void printList(struct ListNode *head) {
    while(head) {
        printf("%d->", head->val);
        head = head->next;
    }
    printf("\n");
}

struct ListNode* partition(struct ListNode* head, int x) {
    struct ListNode* last = NULL;
    struct ListNode* prev = NULL;
    
    struct ListNode* curr = head;
    while (curr && curr->val < x) {
        last = curr;
        curr = curr->next;
    }
    
    while (curr) {
        while (curr && curr->val >= x) {
            prev = curr;
            curr = curr->next;
        }
        if (curr) {
            struct ListNode *next_entry = curr->next;
            if (prev) {
                prev->next = curr->next;
            }
            if (last) {
                curr->next = last->next;
                last->next = curr;
            } else {
                curr->next = head;
                head = curr;
            }
            last = curr;
            curr = next_entry;
            printList(head);
        }
    }
    
    return head;
}


int main(int argc, char *argv[]) {
    struct ListNode a[6];
    struct ListNode *head = &a[0];
    
    a[0].val = 3;
    a[0].next = &a[1];
    a[1].val = 1;
    a[1].next = &a[2];
    a[2].val = 2;
    a[2].next = NULL; // &a[3];
    a[3].val = 2;
    a[3].next = &a[4];
    a[4].val = 5;
    a[4].next = &a[5];
    a[5].val = 2;
    a[5].next = NULL;
    
    printList(head);
    head = partition(head, 3);
    printList(head);
    
}
