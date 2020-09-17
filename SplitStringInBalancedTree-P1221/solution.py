class Solution(object):
    def balancedStringSplit(self, s):
        """
        :type s: str
        :rtype: int
        """
        c=0
        cl=0
        cr=0
        for i in s:
            if i=='L':
                cl=cl+1
            else:
                cr=cr+1
            if cl==cr:
                c=c+1
                cl=0
                cr=0
        return c
        
