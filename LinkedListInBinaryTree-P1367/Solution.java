# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def traverse(self, r, head, c):
        rval=None
        if r is not None:
            rval = r.val
        cval = None
        if c is not None:
            cval = c.val
        #print(rval, cval)
        if c==None:
            return True
        if r == None:
            return False
        if r.val == c.val:
            rb = self.traverse(r.right, head, c.next)
            if rb == True:
                return True
            lb = self.traverse(r.left, head, c.next)
            if lb == True:
                return True
        if r.val == head.val:
            c=head.next
        else:
            c=head
        rb = self.traverse(r.right, head, c)
        if rb == True:
            return True
        lb = self.traverse(r.left, head, c)
        if lb == True:
            return True
        return False
            
    def isSubPath(self, head, root):
        """
        :type head: ListNode
        :type root: TreeNode
        :rtype: bool
        """
        return self.traverse(root, head, head)
        
