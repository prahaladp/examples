class Solution(object):
    def grayCode(self, n):
        """
        :type n: int
        :rtype: List[int]
        """
        cl = []
        if n == 0:
            return cl
        cl.append(0)
        num = 0
        exist = True
        while exist is True:
            mask = 1 << (n-1)
            while mask != 0:
                print mask
                if num & mask:
                    next = num & ~mask
                    if next not in cl:
                        print 'appending ', next
                        cl.append(next)
                        num = next
                        break
                else:
                    next = num | mask
                    if next not in cl:
                        print 'appending ', next 
                        cl.append(next)
                        num = next
                        break
                mask >>= 1
            if mask == 0:
                exist = False
        return cl

s = Solution()
s.grayCode(2)
print s.grayCode(3)
