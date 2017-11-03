//
//  MergeKLists.c
//  
//
//  Created by Prashanth Prahalad on 11/1/17.
//
//

#include <stdio.h>

struct ListNode {
    int val;
    struct ListNode *next;
};

struct ListNode** lists, int listsSize) {
    struct ListNode *smallNode;
    int             indx;
    int             i;
    
    if (listsSize == 0) {
        return NULL;
    }
    
    smallNode = lists[0];
    indx = 0;
    
    for (i = 1; i < listsSize; i++) {
        if (!lists[i]) {
            continue;
        }
        if (!smallNode) {
            indx = i;
            smallNode = lists[i];
            continue;
        }
        if (lists[i]->val < smallNode->val) {
            indx = i;
            smallNode = lists[i];
        }
    }
    
    if (lists[indx]) {
        lists[indx] = lists[indx]->next;
    }
    return smallNode;
}

struct ListNode* mergeKLists(struct ListNode** lists, int listsSize) {
    struct ListNode *head = NULL;
    struct ListNode *prev = NULL;
    struct ListNode *next = NULL;
    
    while ((next = getNextSmallest(lists, listsSize))) {
        if (prev) {
            prev->next = next;
        } else {
            head = next;
        }
        prev = next;
    }
    return head;
}

int main(int argc, char *argv[]) {
    mergeKLists(NULL, 0);
}
