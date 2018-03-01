import sys

class Node(object):
    def __init__(self, index, val):
        self._index = index
        self._val = val

    def get_index(self):
        return self._index
   
    def get_val(self):
        return self._val

class Tree(object):
    def __init__(self, index, val):
        self._root = Node(index, val)   
        self._right = None
        self._left = None

    def add_right(self, tree):
        self._right = tree

    def add_left(self, tree):
        self._left = tree

    def get_lm(self, prefix, consumed):

        if prefix == None:
            return consumed, self._root._val

        c = prefix[0]
        if c == '0' and self._right != None:
            return self._right.get_lm(prefix[1:], consumed + 1)

        if c == '1' and self._left != None:
            return self._left.get_lm(prefix[1:, consumed + 1)

def main(argv):
    str = '1'
    root = Tree(None, None)
    root0 = Tree('0', '10')
    root1 = Tree('1', '01')
    root.add_right(root0)
    root.add_left(root1)

    


if __name__ == "__main__":
    main(sys.argv[1:])
    
