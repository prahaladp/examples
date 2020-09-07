class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        if n == 0:
            return '1'
        if n == 1:
            return '1'
        b = '1'
        n = n - 1
        while n != 0:
            ns = ''
            p = None
            count = 0
            for c in b:
                if c == p:
                    count = count + 1
                elif p is None:
                    count = count + 1
                    p = c
                else:
                    ns += str(count)
                    ns += p
                    p = c
                    count = 1
            if count != 0 and p is not None:
                ns += str(count)
                ns += p
            b = ns
            #print(b)
            n = n - 1
        return b
