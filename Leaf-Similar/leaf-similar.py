class Solution(object):
    def leafSimilar(self, root1, root2):
        """
        :type root1: TreeNode
        :type root2: TreeNode
        :rtype: bool
        """
        l1 = self.findLeaf(root1, [])
        l2 = self.findLeaf(root2, [])
        return l1 == l2
    
    def findLeaf(self, root, l):
        if root is None:
            return l
        if root.left == None and root.right == None:
            return l + [root.val]
        ll = self.findLeaf(root.left, l)
        return self.findLeaf(root.right, ll)
