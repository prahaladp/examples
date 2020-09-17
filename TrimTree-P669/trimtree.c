#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

struct TreeNode* trimBST(struct TreeNode* root, int L, int R) {
    if (root == NULL) {
        return root;
    }

    printf("trim : %d\n", root->val);
    /* regular node */
    if (root->val >= L && root->val <= R) {
        root->left = trimBST(root->left, L, R);
        root->right = trimBST(root->right, L, R);
        return root;
    }
   
    if (root->val < L) {
        return trimBST(root->right, L, R);
    }

    if (root->val > R) {
        return trimBST(root->left, L, R);
    }

    return NULL;
}

void
printTree(struct TreeNode *r) {
    if (r == NULL) {
        return;
    }
    printTree(r->left);
    printf("val = %d\n", r->val);
    printTree(r->right);
}

int
main(int argc, char *argv[]) {
    struct TreeNode arr[10];

    bzero(arr, sizeof (arr));
    arr[0].val = 3;
    arr[1].val = 0;
    arr[2].val = 8;
    arr[3].val = 2;
    arr[4].val = 1;
    arr[0].left = &arr[1];
    arr[0].right = &arr[2];
    arr[1].right = &arr[3];
    arr[3].left = &arr[4];

    printTree(arr);
    struct TreeNode *newTree = trimBST(&arr[0], 4, 4);
    printTree(newTree);
}
