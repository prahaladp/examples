/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

void chkLevel(struct TreeNode *root, int *lvl) {
    if (!root) {
        return;
    }
    
    int leftl = *lvl + 1;
    int rightl = *lvl + 1;
    chkLevel(root->left, &leftl);
    chkLevel(root->right, &rightl);
    if (leftl > rightl) {
        *lvl = leftl;
        return;
    }
    *lvl = rightl;
}

int maxDepth(struct TreeNode* root) {
    if (!root) {
        return 0;
    }
    
    int lvl = 0;
    chkLevel(root, &lvl);
    return lvl;
}
