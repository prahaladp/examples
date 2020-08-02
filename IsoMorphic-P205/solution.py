class Solution(object):
    def isIsomorphic(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        d={}
        if len(s) != len(t):
            return False
        for i in range(0, len(s)):
            if s[i] in d:
                if t[i] != d[s[i]]:
                    return False
            else:
                for i1, (k,v) in enumerate(d.items()):
                    if v == t[i]:
                        return False
                d[s[i]] = t[i]
        return True
