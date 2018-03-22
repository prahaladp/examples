#include<stdio.h>
#include<string.h>
#include<stdlib.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

void moveValues(struct TreeNode *c) {
    struct TreeNode *prev = NULL;
    int             left = 1;

    while (c) {
        if (c->left == NULL && c->right == NULL) {
            break;
        }
        while (c->left) {
            left = 1;
            c->val = c->left->val;
            prev = c;
            c = c->left;
        }
        while (c->right) {
            left = 0;
            c->val = c->right->val;
            prev = c;
            c = c->right;
        }
    }
    if (prev) {
        if (left) {
            prev->left = NULL;
        } else {
            prev->right = NULL;
        }
    }
    // free(c);
}

struct TreeNode* deleteNode(struct TreeNode* root, int key) {
    struct TreeNode *c = root;        

    while (c) {
        if (c->val == key) {
            break;
        }
        if (key < c->val) {
            c = c->left;
            continue;
        }
        c = c->right;
    }

    if (!c) {
        return root;
    }

    moveValues(c);
    return root;
}


void printInorder(struct TreeNode *r) {
    if (!r) {
        return;
    }
    printInorder(r->left);
    printf("%d\n", r->val);
    printInorder(r->right);
}

int main(int argc, char *argv[]) {
    struct TreeNode arr[10];

    bzero(arr, sizeof (struct TreeNode) * 10);
    arr[0].val = 5;
    arr[1].val = 3;
    arr[2].val = 2;
    arr[3].val = 4;
    arr[4].val = 6;
    arr[5].val = 7;
    arr[0].left = &arr[1];
    arr[1].left = &arr[2];
    arr[1].right = &arr[3];
    arr[0].right = &arr[4];
    arr[4].right = &arr[5];
    printInorder(&arr[0]);
    deleteNode(&arr[0], 5);
    printInorder(&arr[0]);
}
