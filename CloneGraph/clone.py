class UndirectedGraphNode:
    def __init__(self, x):
        self.label = x
        self.neighbors = []

    def add_neighbours(self, n):
        self.neighbors.append(n)

class Solution:
    def cloneGraph(self, node):
        node_str = node.split('#')
        node_dct = {}
        for n in node_str:
            vs = n.split(',')
            if len(vs) and vs[0] not in node_dct:
                # add the node
                newn = UndirectedGraphNode(vs[0])
                node_dct[vs[0]] = newn
                for i in range(1, len(vs)):
                    if vs[i] not in node_dct:
                        node_dct[vs[i]] = UndirectedGraphNode(vs[1])
                    newn.add_neighbours(node_dct[vs[i]])
        if len(node_dct.keys()) == 0:
            return None
        return node_dct.keys()[0]

s = Solution()
s.cloneGraph("{0,1,2#1,2#2,2}")


