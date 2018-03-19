#include<stdio.h>
#include<stdlib.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

bool hasPathSum(struct TreeNode* root, int sum) {
    if (!root) {
        return false;
    }
    if (root->val > sum) {
        return false;
    }
    bool l = hasPathSum(root->left, sum - root->val);
    bool r = hasPathSum(root->right, sum - root->val);
    return l | r;
}
