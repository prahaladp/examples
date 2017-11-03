#!/bin/python

class Paranthesis(object):
    def __init__(self, n):
        self._n = n
        self._plist = []

    def generateParenthesis(self, n):
        if n < 0:
            return []

        if n == 0:
            return []

        self._n = n
        self._plist = []
        self._gen('', 0, 0)
        return self._plist

    def _gen(self, s, op, cl):
#        print s, op, cl
        if cl > op:
            return

        if op == self._n and cl == self._n:
            self._plist.append(s)
            return

        if op == self._n:
            self._gen(s + ')', op, cl + 1)
        else:
            self._gen(s + '(', op + 1, cl)
            if op > 0:
                self._gen(s + ')', op, cl + 1)

    def get_list(self):
        return self._plist

def main():
    test_val = [10, 11, 2, 0, 1, -1]
    m = Paranthesis(3)
    for i in test_val:
        print m.generateParenthesis(i)


if __name__ == "__main__":
    main()

