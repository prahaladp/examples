#include<stdio.h>
#include<stdlib.h>
#include<strings.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

int isPalinImpl(struct ListNode** n, struct ListNode *curr, int *dontCompare) {
    if (curr->next) {
        if (isPalinImpl(n, curr->next, dontCompare) == 0) {
            return (0);
        }
        if (*dontCompare == 1) {
            return (1);
        }
    }
    printf("comparing %c %c\n", (*n)->val, curr->val);
    if ((*n)->val == curr->val) {
        if (*n == curr || (*n)->next == curr) {
            *dontCompare = 1;
        }
        *n = (*n)->next;
        return 1;
    }
    return 0;
}

int isPalindrome(struct ListNode* head) {
    struct ListNode **start;
    int             dontCompare = 0;

    if (!head) {
        return 0;
    }
    start = (struct ListNode **)malloc(sizeof (struct ListNode *));
    *start = head;
    printf("comparing %c %c\n", (*start)->val, head->val);
    return isPalinImpl(start, head, &dontCompare);
}

int
main(int argc, char *argv[]) {
    struct ListNode arr[10];

    bzero(arr, sizeof (arr)/sizeof (struct ListNode));
    arr[0].val = 65;
    arr[1].val = 66;
    arr[2].val = 66;
    arr[3].val = 65;
    arr[0].next = &arr[1];
    arr[1].next = &arr[2];
    arr[2].next = &arr[3];
    arr[3].next = 0;
    printf("IsPalin = %c %d\n", arr[0].val, isPalindrome(&arr[0]));

    bzero(arr, sizeof (arr)/sizeof (struct ListNode));
    arr[0].val = 65;
    arr[1].val = 66;
    arr[2].val = 67;
    arr[3].val = 65;
    arr[0].next = &arr[1];
    arr[1].next = &arr[2];
    arr[2].next = &arr[3];
    arr[3].next = 0;
    printf("IsPalin = %c %d\n", arr[0].val, isPalindrome(&arr[0]));
}


