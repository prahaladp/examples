from collections import defaultdict

class Solution(object):
    def frequencySort(self, s):
        """
        :type s: str
        :rtype: str
        """
        cmap = defaultdict(int)
        imap = defaultdict(list)
        for c in s:
            if c not in cmap:
                cmap[c] = 0
            cind = cmap[c]
            try:
               if cind in imap:
                    imap[cind].remove(c)
            except Exception as inst:
                pass
            imap[cind+1].append(c)
            cmap[c] = cind + 1
        k = imap.keys()
        k.sort(reverse=True)
        rstr = ''
        if k is None:
            return rstr
        for key in k:
            for i in imap[key]:
                for ind in range(0, key):
                    rstr += i
        return rstr
