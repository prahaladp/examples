class Solution(object):
    def removeOuterParentheses(self, S):
        """
        :type S: str
        :rtype: str
        """
        l = 0
        outS = ''
        for x in S:
            if x == '(':
                if l != 0:
                    outS += x
                l = l + 1
            if x == ')':
                if l != 1:
                    outS += x
                l = l - 1
        return outS
