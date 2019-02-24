class Solution(object):
    def largeGroupPositions(self, S):
        """
        :type S: str
        :rtype: List[List[int]]
        """
        ind = 0
        l = []
        for c in S:
            if ind == 0:
                beg = 0
                count = 1
                ind = ind + 1
                continue
            if S[ind-1] == S[ind]:
                count = count + 1
            else:
                if count >= 3:
                    iteml = [beg, ind - 1]
                    l += [iteml]
                count = 1
                beg = ind
            ind = ind + 1
        if count >= 3:
            iteml = [beg, ind - 1]
            l += [iteml]
        return l
