class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

import sys

class Solution(object):
    def traverse(self, root):
        l = []
        r = []
        if root.left != None:
            l = self.traverse(root.left)
        if root.right != None:
            r = self.traverse(root.right)
        return l + [root.val] + r

    def minDiffInBST(self, root):
        inorder = self.traverse(root)
        p = inorder[0]
        mindiff = sys.maxint;
        for i in range(1, len(inorder)):
            if inorder[i] - p < mindiff:
                mindiff = inorder[i] - p
            p = inorder[i]
        return mindiff
