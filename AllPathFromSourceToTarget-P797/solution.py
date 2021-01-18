class Solution(object):
    def __init__(self):
        self.graph = []
        self.visited = {}
        
    def dfs(self, nn, cp, res):
        if nn == len(self.graph) - 1:
            res.append(cp + [nn])
            return res
        self.visited[nn] = True
        for i in self.graph[nn]:
            res = self.dfs(i, cp + [nn], res)
        return res
        
    def allPathsSourceTarget(self, graph):
        """
        :type graph: List[List[int]]
        :rtype: List[List[int]]
        """
        self.graph = graph
        self.visited = {}
        res = []
        res = self.dfs(0, [], res)
        return res
