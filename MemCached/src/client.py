import asyncore
import asynchat
import threading
import cStringIO
from protocol import ProtocolBuffer
import logging
svclog = logging.getLogger('')

class ClientMemCacheConn(asynchat.async_chat):
    '''
        Represents a client connection - a client connection
        is assigned a queue on which all it requests are
        processed early on. This queue is assinged by the 
        database load balancer and this ensures that all
        requests are handled sequentially by the db.
        The callbacks are invoked by the aysncore library
        The locking ensures that some of the data structures
        are protected during concurrent access
    '''
    def __init__(self, db, sock):
        asynchat.async_chat.__init__(self, sock=sock)
        self._sock = sock
        self._db = db
        self._buffer = ProtocolBuffer()
        self.set_terminator('\r\n')
        self.rfile = cStringIO.StringIO()
        self.found_terminator = self.handle_request

        # get the queue index associated with this client
        # connection
        self._qindex = self._db.get_queue()

        # number of outstanding requests
        self._outstand = 0
        self._lock = threading.Lock()

    def handle_request(self):
        svclog.debug('handle_request')
        self.rfile.seek(0)
        self._buffer.append(self.rfile.readline())

        # create a new object for the next iteration
        self.rfile = cStringIO.StringIO()
       
        self._lock.acquire()
        self._outstand = self._outstand + 1
        self._lock.release() 

        self._db.submit(self)

    def get_queue_index(self):
        return self._qindex

    def collect_incoming_data(self,data):
        self.rfile.write(data)

    def handle_close(self):
        self.close()

    def get_protocol_buffer(self):
        # read a request 
        return self._buffer

    def get_db(self):
        return self._db

    def lock(self):
        self._lock.acquire()

    def release(self):
        self._lock.release()

    def send_response(self, resp):
        # send the response
        svclog.debug('sending response')
        self._lock.acquire()
        self._outstand = self._outstand - 1
        self._lock.release()

        self.send(resp)
