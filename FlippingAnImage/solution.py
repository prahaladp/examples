class Solution(object):
    def flipAndInvertImage(self, A):
        """
        :type A: List[List[int]]
        :rtype: List[List[int]]
        """
        for r in A:
            i = 0
            j = len(r)-1
            while i <= j:
                if i == j:
                    if r[i] == 0:
                        r[i] = 1
                    else:
                        r[i] = 0
                    break
                tmp = r[i]
                r[i] = r[j]
                r[j] = tmp
                if r[j] == 0:
                    r[j] = 1
                else:
                    r[j] = 0
                if r[i] == 0:
                    r[i] = 1
                else:
                    r[i] = 0
                i = i + 1
                j = j -1
        return A
