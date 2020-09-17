class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.c = 0
        self.tkmap = {}
        self.kvmap = {}
        self.rktmap = {}
        self.tcount = 1
        self.lastt = -1 #empty

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key in self.kvmap:
            tr = self.rktmap[key]
            #print("readjusting ", tr, key)
            del self.tkmap[tr]
            self.tkmap[self.tcount] = key
            self.rktmap[key] = self.tcount
            
            if tr == self.lastt:
                # adjust the last counter
                for l in range(tr+1, self.tcount+1):
                    if l in self.tkmap:
                        self.lastt = l
                        break
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
            del self.tkmap[tr]
            if self.c == 1:
                self.lastt = -1
            elif tr == self.lastt:
                # update the last counter
                for l in range(tr+1, self.tcount+1):
                    if l in self.tkmap:
                        self.lastt = l
                        break
            self.c = self.c - 1
        elif self.c == self.capacity:
            tr = self.lastt
            k = self.tkmap[tr]
            #print("removing ", k, " at position ", tr)
            del self.tkmap[tr] 
            del self.kvmap[k]
            del self.rktmap[k]
            if self.c == 1:
                self.lastt = -1
            else:
                # update the last counter
                for l in range(tr+1, self.tcount+1):
                    if l in self.tkmap:
                        self.lastt = l
                        break
            self.c = self.c - 1
        self.c = self.c + 1
        #print("adding ", self.tcount, key, value)
        self.tkmap[self.tcount] = key
        self.kvmap[key] = value
        self.rktmap[key] = self.tcount
        
        # check if it is the first element
        if self.c == 1:
            self.lastt = self.tcount
        self.tcount += 1


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
