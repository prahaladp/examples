"""
The problem: 
Intput: 
1) list of Ranges that don't overlap (not sorted) 
2) newRange that might overlap. 
Output: 
list of merged Ranges 

For example: 
Input: [1..5] [9..13] [17..22] 
newRange: [4..10] 
Output: [1..13] [17..22]
"""
class Range(object):
    def __init__(self, f, s):
        self._begin = f
        self._end = s

    def overlap(self, range):
        if range._begin >= self._begin and range._begin <= self._end:
            return True
        if range._end >= self._begin and range._end <= self._end:
            return True
        return False

    def merge(self, range):
        if range._begin < self._begin:
            self._begin = range._begin
        if range._end > self._end:
            self._end = range._end

    def __repr__(self):
        return '[%d, %d]' % (self._begin, self._end)

def merge_ranges(range_list, new_range):
    len_range = len(range_list)

    indx_range = 0
    stop_on_no_overlap = False

    while indx_range < len_range:
        if range_list[indx_range].overlap(new_range):
            range_list[indx_range].merge(new_range)
            next_indx = indx_range + 1
            while next_indx < len_range:
                if range_list[indx_range].overlap(range_list[next_indx]):
                    new_range = range_list.pop(next_indx)
                    range_list[indx_range].merge(new_range)
                else:
                    return
        indx_range = indx_range + 1

range_list = []
range_list.append(Range(1,5))
range_list.append(Range(9,13))
range_list.append(Range(17,22))
merge_ranges(range_list, Range(6,7))
print 'new range list is : ', range_list
