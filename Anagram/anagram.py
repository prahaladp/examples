
class AnagramChecker(object):
    def __init__(self, al):
        self._list = al

    def _check(self):
        self._group = {}

        for l in self._list:
            match = None
            for k in self._group.keys():
                if sorted(k) == sorted(l):
                    match = k
                    break

            if match == None:
                self._group[l] = []
                match = l

            self._group[match].append(l)

    def _print(self):
        for k in self._group:
            for l in self._group[k]:
                print l

strlist = [ 'cat', 'act', 'noops', 'oto', 'act', 'too', 'tac', 'spoon']
al = AnagramChecker(strlist)
al._check()
al._print() 
