class Solution(object):
    def sortArrayByParity(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """
        e=[]
        o=[]
        for x in A:
            if x % 2 == 0:
                e.append(x)
            else:
                o.append(x)
        return e + o
