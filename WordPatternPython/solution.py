class Solution(object):
    def wordPattern(self, pattern, str):
        """
        :type pattern: str
        :type str: str
        :rtype: bool
        """
        tok = str.split(' ')
        if len(pattern) != len(tok):
            return False
        d = {}
        ind = 0
        for t in tok:
            l = pattern[ind]
            if l in d:
                if t != d[l]:
                    return False
            else:
                for v in d.values():
                    if v == t:
                        return False
                d[l] = t
            ind = ind + 1
        return True
        
