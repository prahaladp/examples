import re
from file_ops import Dir
from file_ops import File
from download import Download

class CityParser(object):
    def __init__(self, state, file):
        self._file = file
        self._state = state

    def parse(self):
        city_list = []
        for line in self._file.reader():
            ftok = re.split(" schools\n", line)
            if len(ftok) == 2:
                pattern = "/" + self._state + "\">"
                stok = re.split(pattern, ftok[0])
                if len(stok) == 2:
                    city_list.append(stok[1])

        return city_list

class CitySchoolRatingParser(object):
    def __init__(self, file):
        self._file = file

    def parse(self):
        for line in self._file.reader():
            tok=re.split('</span><br />out of 10</div>', line)
            if len(tok) == 2:
                stok=re.split('<span class=\"jumbo-text\">', tok[0])
                if len(stok) == 2:
                    return stok[1]

        return 0
