class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.t = []
        self.c = 0
        self.tkmap = {}
        self.kvmap = {}
        self.rktmap = {}
        self.tcount = 1
        heapq.heapify(self.t)

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key in self.kvmap:
            tr = self.rktmap[key]
            #print("readjusting ", tr, key)
            self.t.remove(tr)
            heapq.heapify(self.t)
            self.tkmap[self.tcount] = key
            self.rktmap[key] = self.tcount
            heapq.heappush(self.t, self.tcount)
            self.tcount += 1
            return self.kvmap[key]
        return -1

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.kvmap:
            tr = self.rktmap[key]
            self.t.remove(tr)
            heapq.heapify(self.t)
        elif self.c == self.capacity:
            tr = heapq.heappop(self.t)
            k = self.tkmap[tr]
            #print("removing ", k, " at position ", tr)
            del self.tkmap[tr] 
            del self.kvmap[k]
            del self.rktmap[k]
        else:
            self.c = self.c + 1

        #print("adding ", self.tcount, key, value)
        self.tkmap[self.tcount] = key
        self.kvmap[key] = value
        self.rktmap[key] = self.tcount
        heapq.heappush(self.t, self.tcount)
        self.tcount += 1


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
