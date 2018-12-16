class Solution(object):
    def maxDistToClosest(self, seats):
        """
        :type seats: List[int]
        :rtype: int
        """
        print seats
        l = len(seats)
        for i in range(0, l):
            if seats[i] == 1:
                continue
            if seats[i] == 0:
                if i > 0 and seats[i-1] != 0:
                    seats[i] = seats[i-1] + 1

        print seats
        for i in range(0, l):
            if seats[i] == 1:
                continue
            if seats[i] == 0:
                continue
            #seats[i] = seats[i] - 1

        print seats
        mi = l-1
        mv = seats[l-1]
        for i in range(l-1,-1,-1):
            if seats[i] == 1:
                continue
            if i == l-1:
                continue
            nl = seats[i+1]+1
            if nl < seats[i] or seats[i] == 0:
                seats[i] = nl

            if seats[i] > mv:
                mv = seats[i]
        print seats

        return mv-1

s = Solution()
print s.maxDistToClosest([1,0,0,0,1,0,1])
print s.maxDistToClosest([0,0,1])

        
