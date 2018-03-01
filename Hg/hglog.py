import re
import sys

class ChangeSet(object):
    def __init__(self, cs_info):
        tokens = cs_info.split(':')
        self._key = tokens[2].rstrip().lstrip()
        self._rev = tokens[1].rstrip().lstrip()

    def get_key(self):
        return self._key

    def _get_user(self, f):
        for line in f:
            if line.find('user') != -1:
                self._user = line.split(':')[1].rstrip().lstrip()
                break

    def _get_date(self, f):
        for line in f:
            if line.find('date') != -1:
                self._date = line.split(':')[1].rstrip().lstrip()
                break

    def _get_description(self, f):
        for l in f:
            if len(l) == 1:
                break

    def __repr__(self):
        return ('[%s, %s, %s, %s]' % (
            self._key, self._rev, self._user, self._date))

    def fill_info(self, f):
        self._get_user(f)
        self._get_date(f)
        self._get_description(f)

class HgLogParser(object):
    def __init__(self, filename):
        self._file = filename

    def parse(self):
        cs_dict = {}

        with open(self._file, 'r') as f:
            for line in f:
                # split into tokens
                tokens = line.split(':')
                if tokens[0] == 'changeset':
                    cs = ChangeSet(line)
                    cs.fill_info(f)
                    # add to the changelist
                    cs_dict[cs.get_key()] = cs

        return cs_dict

hglogparser = HgLogParser(sys.argv[1])
changelists = hglogparser.parse()

print 'Total Changelists : ', len(changelists)
for c in changelists:
    print changelists[c]
