class MedianFinder(object):
    l = []
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.l=[]

    def addNum(self, num):
        """
        :type num: int
        :rtype: None
        """
        print 'start : ', self.l
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
            print self.l, s, e
            if s == e:
                if num < self.l[s]:
                    self.l.insert(s, num)
                    return
                self.l.insert(s+1,num)
                return
            m = s + (e-s)/2
            print 'mid : ', m
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
            return self.l[m]
        m = le/2
        return (self.l[m] + self.l[m-1])/2

s = MedianFinder()
s.addNum(0)
s.addNum(10)
s.addNum(4)
s.addNum(5)
s.addNum(-1)
s.addNum(100)
s.addNum(43)
