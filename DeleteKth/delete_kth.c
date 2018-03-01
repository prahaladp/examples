#include<stdio.h>
#include<stdlib.h>
#include<string.h>

struct node_s {
    int             val;
    struct node_s   *next;
};

struct node_s *delete_kth(struct node_s *head, int k) {
    struct node_s   *prev = NULL;
    struct node_s   *curr = head;
    struct node_s   *nh = head;
    int ck = k;

    printf("deleting %d node\n", k);
    if (k == 0) {
        return head;
    }

    while (curr != NULL) {
        ck--;
        if (ck == 0) {
            /* remove the current element */
            if (prev) {
                prev->next = curr->next;
            } else {
                nh = curr->next;
            }
            curr = curr->next;
            ck = k;
        } else {
            prev = curr;
            curr = curr->next;
        }
    }

    return nh;
}

void print_tree(struct node_s *head) {
    struct node_s   *curr = head;
    printf("printing tree\n");
    while (curr) {
        printf("val = %d\n", curr->val);
        curr = curr->next;
    }
}

int main(int argc, char *argv[]) {
    struct node_s arr[10];
    struct node_s *head;

    bzero(arr, 10 * sizeof (struct node_s));
    arr[0].next = &arr[1]; arr[0].val = 0;
    arr[1].next = &arr[2]; arr[1].val = 1;
    arr[2].next = &arr[3]; arr[2].val = 2;
    arr[3].next = NULL; arr[3].val = 3;
    print_tree(&arr[0]);
    head = delete_kth(arr, 4);
    print_tree(head);

    bzero(arr, 10 * sizeof (struct node_s));
    arr[0].next = &arr[1]; arr[0].val = 0;
    arr[1].next = &arr[2]; arr[1].val = 1;
    arr[2].next = &arr[3]; arr[2].val = 2;
    arr[3].next = &arr[4]; arr[3].val = 3;
    arr[4].val = 4;
    print_tree(&arr[0]);
    head = delete_kth(arr, 2);
    print_tree(head);

    bzero(arr, 10 * sizeof (struct node_s));
    arr[0].next = &arr[1]; arr[0].val = 0;
    arr[1].next = &arr[2]; arr[1].val = 1;
    arr[2].next = &arr[3]; arr[2].val = 2;
    arr[3].next = &arr[4]; arr[3].val = 3;
    arr[4].val = 4;
    print_tree(&arr[0]);
    head = delete_kth(arr, 1);
    print_tree(head);

    bzero(arr, 10 * sizeof (struct node_s));
    arr[0].next = &arr[1]; arr[0].val = 0;
    arr[1].next = &arr[2]; arr[1].val = 1;
    arr[2].next = &arr[3]; arr[2].val = 2;
    arr[3].next = &arr[4]; arr[3].val = 3;
    arr[4].val = 4;
    print_tree(&arr[0]);
    head = delete_kth(arr, 3);
    print_tree(head);

}
