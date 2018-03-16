#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

int findTiltImpl(struct TreeNode* root, int *sum) {
    int     tilt;

    *sum = 0;
    if (!root) {
        return 0;
    }

    int rsum = 0;
    int lsum = 0;
    int rtilt = findTiltImpl(root->right, &rsum);
    int ltilt = findTiltImpl(root->left, &lsum);

    tilt = abs(rsum - lsum);
    *sum += root->val + rsum + lsum;
    return tilt + rtilt + ltilt;
}

int findTilt(struct TreeNode* root) {
    int sum = 0;
    return findTiltImpl(root, &sum);
}

int main(int argc, char *argv[]) {
    struct TreeNode arr[5];

    printf("sizeof (arr) = %d\n", sizeof (arr));
    printf("sizeof (TreeNode) = %d\n", sizeof (struct TreeNode));
    bzero(arr, sizeof (arr));
    arr[0].val = 1;
    arr[0].left = &arr[1];
    arr[0].right = &arr[2];
    arr[1].val = 2;
    arr[1].left = &arr[3];
    arr[2].val = 3;
    arr[2].left = &arr[4];
    arr[3].val = 4;
    arr[4].val = 5;
    printf("Tilt = %d\n", findTilt(&arr[0]));
}
