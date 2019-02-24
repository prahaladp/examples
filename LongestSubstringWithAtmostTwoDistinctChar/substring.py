class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        ls = 0
        cset = []
        cmap = {}
        cindx = 0
        start = 0
        ans = ''
        
        for x in s:
            if x in cset:
                if cindx - start > ls:
                    ans = s[start:cindx]
                    ls = cindx - start
                for i in  range(start, cmap[x] + 1):
                    cset.remove(s[i])
                start = cmap[x] + 1

            cset.append(x)
            cmap[x] = cindx
            cindx += 1
        if cindx - start > ls:
            ans = s[start:cindx]
            ls = cindx - start
        return ls

