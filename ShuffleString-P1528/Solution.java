class Solution(object):
    def restoreString(self, s, indices):
        """
        :type s: str
        :type indices: List[int]
        :rtype: str
        """
        
        # pick the first index not in its position
        ns = []
        ns = [x for x in s]        
        for i in range(0, len(indices)):
            if indices[i] != i:
                ns[indices[i]] = s[i]
        str1=''
        return str1.join(ns)

