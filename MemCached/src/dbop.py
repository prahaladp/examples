from keyval import KeyVal
import logging
svclog = logging.getLogger('')

#
# This file contains all the DB operations. The base class
# for this is the DbOperation class. Each class has a 
# parse method and and execute method. The parse method
# is a factory pattern which returns an instance of the relevant
# operation once it has passed the parsing and has all the required
# parameters. For a valid operation, this object contains all
# required information and it is submitted to the database
#
# Finally the execute packages the information from the database
# into the required format for the get/set operations on the database
#
# The main classes to look are the DbGet and DbSet classes
class DbOperation(object):
    def __init__(self):
        pass

    def process(self):
        pass

class DbSet(DbOperation):
    def __init__(self, key_val):
        self._key_val = key_val

    @staticmethod
    def parse(tokens, data):
        if len(tokens) != 5:
            return InvalidDbSet('malformed set request')

        key = tokens[1]
        flags = tokens[2]
        exptime = tokens[3]
        sz = tokens[4]

        # TBD, need to validate the size of the data block
        if len(data) != int(sz):
            svclog.debug('data size received is incorrect')
            svclog.debug(('expecting %d, received %d' % (int(sz), len(data))))
            return InvalidDbSet('incorrect data size')

        key_val = KeyVal(key, flags, exptime, sz, data)
        return DbSet(key_val)

    def execute(self, db):
        db[self._key_val.get_key()] = self._key_val
        return 'STORED\r\n'

class InvalidDbSet(DbOperation):
    def __init__(self, msg):
        self._msg = msg

    def execute(self, db):
        svclog.debug((self._msg))
        return 'NOT_STORED\r\n'

class InvalidDbGet(DbOperation):
    def __init__(self, msg):
        self._msg = msg

    def execute(self, db):
        svclog.debug((self._msg))
        return 'END\r\n'

class DbGet(DbOperation):
    def __init__(self, key):
        self._key = key

    @staticmethod
    def parse(tokens):
        # parse and create a new required object
        if len(tokens) <= 1:
            return InvalidDbGet('malformed get request')
        return DbGet(tokens[1])

    def execute(self, db):
        if self._key not in db:
            # generate a empty response back
            return 'END\r\n'

        key_val = db[self._key]
        return ('VALUE %s %s %s\r\n%s\r\n') % (
                        key_val.get_key(),
                        key_val.get_flags(),
                        key_val.get_sz(),
                        key_val.get_val()
                    )
