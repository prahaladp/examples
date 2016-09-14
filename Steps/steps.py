
#
# start step, end step, max (x,y)
#

minpath = 65535

class Coord(object):
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def compare(self, otherxy):
        if otherxy.x == self.x and otherxy.y == self.y:
            return True
        return False

    def contains(self, otherxy):
        if otherxy.x < 0 or otherxy.x > self.x:
            return False
        if otherxy.y < 0 or otherxy.y > self.y:
            return False
        return True

def find_next_step(currxy, destxy, maxxy, path_len):
    print 'next_step : ', currxy.x, currxy.y
    global minpath

    if maxxy.contains(currxy) == False:
        print 'beyond matrix : ', currxy.x, currxy.y
        return

    if currxy.compare(destxy) == True:
        print "found path with length %d" % (path_len)
        if path_len < minpath:
            minpath = path_len
        return

    x = currxy.x
    y = currxy.y
    nextxy = Coord(x, y)
    nextxy.y = y + x
    xlen = find_next_step(nextxy, destxy, maxxy, path_len + 1)

    nextxy = Coord(x, y) 
    nextxy.x = x + y
    ylen = find_next_step(nextxy, destxy, maxxy, path_len + 1)
    return 

maxxy = Coord(100,100)
destxy = Coord(45,34)
startxy = Coord(1,1)

find_next_step(startxy, destxy, maxxy, 0)
print "Minimum path = " + str(minpath)
 
