class Solution(object):
    l = []

    def medianSlidingWindow(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[float]
        """
        if k > len(nums):
            return []
        s = 0
        med = []
        self.l=[]
        for i in range(s, k-1):
            self.addNum(nums[i])
        while k <= len(nums):
            self.addNum(nums[k-1])
            med.append(self.findMedian())
            self.findAndRemove(nums[s])
            s = s + 1
            k = k + 1

        return med
    
    def findAndRemove(self, n):
        s = 0
        e = len(self.l) - 1
        while s <= e:
            m = s + (e-s)/2
            if (self.l[m] == n):
                self.l.pop(m)
                return
            if (n > self.l[m]):
                s = m + 1
            else:
                e = m -1

    def addNum(self, num):
        """
        :type num: int
        :rtype: None
        """
        if len(self.l) == 0:
            self.l.append(num)
            return
        if len(self.l) == 1:
            if num < self.l[0]:
                self.l.insert(0,num)
                return
            self.l.append(num)
            return

        s=0
        e=len(self.l) - 1
        f=False
        while f is False:
            if s == e:
                if num < self.l[s]:
                    self.l.insert(s, num)
                    return
                self.l.insert(s+1,num)
                return
            m = s + (e-s)/2
            if num < self.l[m]:
                e = m-1
            elif num > self.l[m]:
                s = m + 1
            else:
                self.l.insert(m, num)
                return
            if e < s:
                self.l.insert(s, num)
                return
            elif s > e:
                self.l.insert(e, num)
                return
        return

    def findMedian(self):
        """
        :rtype: float
        """
        le = len(self.l)
        if le == 0:
            return 0
        if le % 2 != 0:
            m = (le-1)/2
            return (float)(self.l[m])
        m = le/2
        return (float)(self.l[m] + self.l[m-1])/(float)(2.0)
        
