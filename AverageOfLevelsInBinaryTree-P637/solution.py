# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def averageOfLevels(self, root):
        """
        :type root: TreeNode
        :rtype: List[float]
        """
        next = [root]
        avglist = []
        while len(next) != 0:
            curr = next
            next = []
            sum = 0
            for x in curr:
                sum += x.val
                if x.left != None:
                    next.append(x.left)
                if x.right != None:
                    next.append(x.right)
            avg = float(sum)/float(len(curr))
            avglist.append(avg)
        return avglist

