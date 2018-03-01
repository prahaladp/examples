import asyncore
import asynchat
import socket
import cStringIO
from Queue import Queue
import logging
import log
from memcachedb import MemCacheDb
from client import ClientMemCacheConn

class MemCached(asyncore.dispatcher):
    '''
        The main DB server class which creates the socket which
        listens for requests from the clients. It uses the asyncore
        library to handle incoming requests and then creating new
        sockets as the accept calls succeed. During cleanup it sends
        out appropriate message to the DB and the worker threads to
        cleanup and run to completion before exiting
    '''
    def __init__(self, host, port):
        asyncore.dispatcher.__init__(self)
        self.create_socket(socket.AF_INET, socket.SOCK_STREAM)
        self.set_reuse_addr()
        self.bind((host, port))
        self.listen(5)
        self._db = MemCacheDb()

    def handle_accept(self):
        svclog.debug('accept')
        sock_pair = self.accept()

        # instantiate new connection
        if sock_pair is not None:
            sock, addr = sock_pair
            conn = ClientMemCacheConn(self._db, sock)

    def run(self):
        try:
            # loop on the socket descriptors
            asyncore.loop(timeout=2)
        except KeyboardInterrupt as e:
            svclog.debug('received termination')
            self._db.wait_completion()

    def wait(self):
        try:
            self._db.wait()
        except KeyboardInterrupt as e:
            svclog.debug('received termination')
            self._db.wait_completion()

if __name__ == "__main__":
    log.init()
    global svclog
    svclog = logging.getLogger('') 
    svclog.debug('test')
    port = 11211
    memc = MemCached('0.0.0.0', port)
    memc.run()
