/**
 * Definition for a binary tree node.
 */
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef bool int;

bool isSymmetric(struct TreeNode* root) {
    struct TreeNode *right;
    struct TreeNdoe *left;

    if (!root) {
        return 0;
    }

    left = root->left;
    right = root->right;

    if (!left && !right) {
        return 1;
    }

    if (left && !right) {
        return 0;
    }

    if (right && !left) {
        return 0;
    }

    if (right->val != left->val) {
        return 0;
    }

    return isSymmetric(root->left) && isSymmetric(root->right);
}
