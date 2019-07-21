/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

int rangeSumBST(struct TreeNode* root, int L, int R){
    if (root == NULL) {
        return 0;
    } 
    int s = rangeSumBST(root->left, L, R);
    if (root->val >= L && root->val <= R) {
        s += root->val;
    }
    s += rangeSumBST(root->right, L, R);
    return s;
}
