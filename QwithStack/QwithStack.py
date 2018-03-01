"""
    S1 is used for enqueuing
        To enqueue - 
            ensure S2 is pushed onto S1
            push the new object
    S2 is used for dequeuing
        To dequeue -
            push all items from S1 onto S2
            pop the first item out
"""
class Q(object):
    def __init__(self, sz):
        self._sz = sz
        self._s1 = Stack(sz)
        self._s2 = Stack(sz)

    def enq(self, val):
        for i in self._s2.pop_iter():
            self._s1.push(i)

        self._s1.push(val)

    def deq(self):
        for i in self._s1.pop_iter():
            self._s2.push(i)

        return (self._s2.pop())

class Stack(object):
    def __init__(self, sz):
        self._max_sz = sz
        self._sz = -1
        self._items = []

    def push(self, val):
        self._sz = self._sz + 1
        self._items.insert(self._sz, val)

    def pop(self):
        print self._items
        if self._sz < 0:
            raise Exception()

        val = self._items.pop(self._sz)
        self._sz = self._sz - 1 
        return val

    def pop_iter(self):
        while self._sz >= 0:
            yield self.pop()

q = Q(10)
q.enq(10)
q.enq(20)
print q.deq()
print q.deq()
print q.enq(400)
print q.deq()
