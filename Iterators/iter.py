
class Iterator(object):
    def __init__(self, l):
        self._l = l
        self._indx = 0

    def next(self):
        if self._indx >= len(self._l):
            return None
        val = self._l[self._indx]
        self._indx = self._indx + 1
        return val

def iterate(iterlist):
    more_items = True
    item_list = []

    while more_items:
        more_items = False
        for iter in iterlist:
            ni = iter.next()
            if ni != None:
                more_items = True
                item_list.append(ni)

    return item_list

a = Iterator([1, 2, 3])
b = Iterator(['b1'])
c = Iterator(['c1', 'c2', 'c3'])

final_list = iterate([a, b, c])
print final_list
            
