import os

class FSEntry(object):
    def __init__(self, path):
        self._path = path

    def exists(self):
        return os.path.exists(self._path)

class Dir(FSEntry):
    def create_if_needed(self):
        if not os.path.exists(self._path):
            os.makedirs(self._path)

class File(FSEntry):
    def create_if_needed(self):
        pass

    def reader(self):
        print 'reading %s' % (self._path)
        with open(self._path, 'r') as f:
            for line in f:
                yield line
