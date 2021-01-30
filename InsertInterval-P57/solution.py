class Tree(object):
    def __init__(self, interval):
        self._interval = interval
        self._left = None
        self._right = None
        
class Solution(object):
    def isOverlap(self, ni, ci):
        if (ni[0] >= ci[0] and ni[0] <= ci[1]) or (ni[0] < ci[0] and ni[1] >= ci[0]):
            return True
        if ni[1] >= ci[0] and ni[1] <= ci[1]:
            return True
        return False
        
    def insert(self, iv, ni):
        """
        :type intervals: List[List[int]]
        :type newInterval: List[int]
        :rtype: List[List[int]]
        """
        if len(iv) == 0:
            return [ni]
        for i in range(len(iv)):
            if ni[1] < iv[i][0]:
                iv = iv[:i] + [ni] + iv[i:]
                break
            if ni[0] > iv[i][1]:
                if i == len(iv)-1:
                    iv = iv + [ni]
                    break
                continue
            if i!= len(iv)-1:
                if self.isOverlap(ni, iv[i]):
                    j=i+1
                    while j < len(iv) and self.isOverlap(ni, iv[j]) is True:
                        j = j + 1
                    iv = iv[:i] + [[min(ni[0], iv[i][0]), max(ni[1], iv[j-1][1])]] + iv[j:]
                    break
            elif self.isOverlap(ni, iv[i]):
                iv = iv[:i] + [[min(ni[0], iv[i][0]), max(ni[1], iv[i][1])]]
                break
            else:
                iv = iv + [ni]
                break
        return iv
