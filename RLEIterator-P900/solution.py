class RLEIterator(object):

    def __init__(self, A):
        """
        :type A: List[int]
        """
        self.A = A
        self.e = [A[i] for i in  range(0, len(A),2)]
        self.i = 0
        
    def next(self, n):
        """
        :type n: int
        :rtype: int
        """
        if self.i > len(self.e):
            return -1
        while self.i < len(self.e): 
            if n <= self.e[self.i]:
                val = self.A[self.i * 2 + 1]
                self.e[self.i] -= n
                if self.e[self.i] == 0:
                    self.i += 1
                return val
            else:
                n-=self.e[self.i]
                self.i += 1
        
        return -1

# Your RLEIterator object will be instantiated and called as such:
# obj = RLEIterator(A)
# param_1 = obj.next(n)
