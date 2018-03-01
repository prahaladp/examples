class LFUCache(object):
    
    def __init__(self, capacity):
        """
            :type capacity: int
            """
        self._cap = capacity;
        self._kv = {}
        self._kv_get_ts = []
        self._kv_put_ts = []
    
    def remove_key_from_ts(self, list, key):
        indx = self.get_index(list, key)
        if indx != -1:
            # remove the key from the list
            list.pop(indx)

    def get(self, key):
        """
            :type key: int
            :rtype: int
            """
        
        if key in self._kv:
            
            val = self._kv[key]
            
            self.remove_key_from_ts(self._kv_put_ts, key)
            self.remove_key_from_ts(self._kv_get_ts, key)
            
            # insert it at the top of the list
            self._kv_get_ts.insert(0, key)
            self._kv[key] = val
            
            return val
        
        return -1
    
    def get_index(self, list, key):
        indx = -1
        for i in range(0, len(list)):
            if list[i] == key:
                indx = i
                break
        return indx

    def put(self, key, value):
        """
            :type key: int
            :type value: int
            :rtype: void
            """
        if key is None:
            return
        
        if self._cap == 0:
            # if no capcacity, return
            self._kv = {}
            self._kv_get_ts = []
            self._kv_put_ts = []
            return
        
        if key not in self._kv and len(self._kv.keys()) == self._cap:
            
            # peak capacity
            # try to remove at the end of the put list
            if len(self._kv_put_ts):
                delkey = self._kv_put_ts.pop()
            else:
                # pop the key at the last of get list
                delkey = self._kv_get_ts.pop()
            
            # remove the key from the hashmap
            self._kv.pop(delkey, None)
        
        # insert the new value at index 0
        self._kv[key] = value
        
        if key not in self._kv_get_ts:
            # object is just in the put list, move it he beginning
            self.remove_key_from_ts(self._kv_put_ts, key)
            self._kv_put_ts.append(key)

    def print_state(self):
        print '------'
        print 'get ', self._kv_get_ts
        print 'put ', self._kv_put_ts
        print '------'

# Your LFUCache object will be instantiated and called as such:
obj = LFUCache(3)
obj.put(2,2)
obj.put(1,1)
print 'get(2) = ', obj.get(2)
print 'get(1) = ', obj.get(1)
print 'get(2) = ', obj.get(2)
obj.put(3,3)
obj.put(4,4)
print 'get(3) = ', obj.get(3)
print 'get(2) = ', obj.get(2)
print 'get(1) = ', obj.get(1)
print 'get(4) = ', obj.get(4)

print '-----'
obj = LFUCache(2)
obj.put(3,1)
obj.put(2,1)
obj.put(2,2)
obj.put(4,4)
print 'get(2) = ', obj.get(2)


print '-----'
obj = LFUCache(2)
obj.put(2,1)
obj.put(2,2)
print 'get(2) = ', obj.get(2)
obj.put(1,1)
obj.put(4,1)
print 'get(2) = ', obj.get(2)

null = None
test = ["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
test_val = [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]
expected_val = [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]

print '-----'
i = 0
obj = None
for t in test:
    if t == "LFUCache":
        obj = LFUCache(test_val[i][0])
    elif t == "put":
        key = test_val[i][0]
        val = test_val[i][1]
        obj.put(key, val)
        print 'put(' + str(key) + ',' + str(val) + ')'
        obj.print_state()
    elif t == "get":
        key = test_val[i][0]
        val = obj.get(key)
        print 'get(%d) = (%d)' % (key, val)
        obj.print_state()
        if val != expected_val[i]:
            print '>>>>>> incorrect val, expected val = ', expected_val[i]


    i = i + 1


