import queue 

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def widthOfBinaryTree(self, root: 'TreeNode') -> 'int':
        newQ = None
        oldQ = queue.Queue()
        oldQ.put(root)
        mw = 1
        while oldQ.empty() is False:
            newQ = queue.Queue()
            nz = 0
            while oldQ.empty() is False:
                print('%d' % (oldQ.qsize()))
                iq = oldQ.get(block=False)
                if iq is None:
                    break
                if iq.left == None and iq.right == None:
                    continue
                if iq.left != None:
                    newQ.put(iq.left)
                if iq.right != None:
                    newQ.put(iq.right)
                nz += 2
            if nz > mw:
                mw = nz
            oldQ = newQ
        return mw

s = Solution()
ro = TreeNode(1)
l = TreeNode(2)
r = TreeNode(3)
ro.left = l
ro.right = r
print('size = %d' % (s.widthOfBinaryTree(ro)))
