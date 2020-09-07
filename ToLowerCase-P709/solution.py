class Solution(object):
    def toLowerCase(self, str):
        """
        :type str: str
        :rtype: str
        """
        newS = ''
        for x  in str:
            if x >= 'A' and x <= 'Z':
                s = ''
                s+=x
                newS += s.lower()
            else:
                newS += x
        return newS
        
