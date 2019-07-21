class Solution(object):
    def reverseWords(self, s):
        """
        :type s: str
        :rtype: str
        """
        outstr=""
        indx = 0
        si = -1
        for i in s:
            if i == ' ':
                ei = indx - 1
                if si != -1:
                    if len(outstr) == 0:
                        outstr = s[si:indx]
                    else:
                        outstr = s[si:indx] + " " + outstr
                si = -1
                indx = indx + 1
                continue
            
            if si == -1:
                si = indx

            indx = indx + 1
            
        if si != -1:
            if len(outstr) == 0:
                outstr = s[si:indx]
            else:
                outstr = s[si:indx] + " " + outstr

