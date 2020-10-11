class Solution(object):
    def ladderLength(self, bw, ew, wordList):
        """
        :type beginWord: str
        :type endWord: str
        :type wordList: List[str]
        :rtype: int
        """

        import collections
        wo = collections.defaultdict(list)
        for w in wordList:
            for i in range(0, len(w)):
                wo[w[:i] + "*" + w[i + 1:]].append(w)
        visited = []
        queue = [bw]
        l = 0
        while len(queue) != 0:
            nq = []
            l = l + 1
            for q in queue:
                #print(l, queue, visited)
                #input()
                if q in visited:
                    continue
                visited.append(q)
                for i in range(0, len(q)):
                    iw = q[:i] + "*" + q[i + 1:]
                    for w in wo[iw]:
                        if w == ew:
                            return l + 1
                        if w in visited:
                            continue
                        nq.append(w)
            queue = nq
        return 0

