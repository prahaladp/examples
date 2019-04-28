void checkBalanced(struct TreeNode *root, int *tDepth, bool *isBal) {
    int lD = 0;
    int rD = 0;
    
    *isBal = false;
    
    if (root == NULL) {
        *isBal = true;
        *tDepth = 0;
        return;
    }
    
    checkBalanced(root->left, &lD, isBal);
    if (*isBal == false) {
        return;
    }
    checkBalanced(root->right, &rD, isBal);
    if (*isBal == false) {
        return;
    }

    //printf("root : %d, ld = %d, rd = %d\n", root->val, lD, rD);
    *isBal = (abs(lD-rD) <= 1) ? true : false;
    *tDepth = (lD > rD) ? lD + 1: rD + 1;
}

bool isBalanced(struct TreeNode* root) {
    int d;
    bool isBal;
    
    if (root == NULL) {
        return true;
    }
    
    checkBalanced(root, &d, &isBal);
    return isBal;
}
