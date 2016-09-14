from dbop import DbGet, DbSet
import logging
svclog = logging.getLogger('')

class ProtocolBuffer(object):
    '''
        The protocol buffer class which handles the incoming protocol
        requests and services them. The parser ensures that the packets
        are correctly formatted before submitting to the database. It also
        handles cases where buffering might be necessary across the socket
        calls
    '''
    def __init__(self):
        self._buffer = []

    def append(self, buffer):
        'appending buffer = %s', buffer
        self._buffer.append(buffer)

    def parse(self):
        objs = []

        # get a request at a time, start at the top
        while len(self._buffer):
            cmd = self._buffer.pop(0)
            tokens = cmd.split(' ')

            svclog.debug(('command = ', tokens[0]))

            if tokens[0] == 'get':
                objs.append(DbGet.parse(tokens))

            if tokens[0] == 'set':
                if len(self._buffer) == 0:
                    # push the buffer back for the next pass
                    # and exit
                    svclog.debug(('waiting for set <data block>'))
                    self._buffer.insert(0, cmd)
                    break

                set_data = self._buffer.pop(0)
                objs.append(DbSet.parse(tokens, set_data))

        return objs
