class Node(object):
    def __init__(self, h, num):
        self._h = h
        self._n = num
        self._right = None
        self._left = None
        self._nr = 0
        self._nl = 0

    def insert(self, node):
        if node._h <= self._h:
            if node._n >= 1:
                node._right = self
                node._nr = self._nr + self._nl + 1
                return node
            # has to be inserted to the right of this node
            if self._right is not None:
                self._right = self._right.insert(node)
                self._nr = self._nr + 1
                return self
            else:
                self._right = node
                self._nr = self._nr + 1
                return self

class Solution(object):
        def reconstructQueue(self, people):
            r = None
            v = []
            for p in people:
                v.append(0)

            for p in people:
                if r is None:
                    r = []
                    r.append(p)
                    if p[1] == 0:
                        v[0] = 1
                    continue


