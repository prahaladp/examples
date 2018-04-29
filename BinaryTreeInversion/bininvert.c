#include<stdio.h>
#include<stdlib.h>

struct TreeNode {
     int val;
     struct TreeNode *left;
     struct TreeNode *right;
};

struct TreeNode* invertTree(struct TreeNode* root) {
    struct TreeNode *left;
    struct TreeNode *right;
    
    if (!root) {
        return root;
    }
    
    left = root->left;
    right = root->right;
    
    root->left = right;
    root->right = left;
    if (root->left) {
        invertTree(root->left);
    }
    
    if (root->right) {
        invertTree(root->right);
    }
    
    return root;
}

int main(int argc, char *argv[]) {
    
}
