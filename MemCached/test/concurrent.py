import multiprocessing
import time
import socket
import logging
import log
import random
import socket
import sys

log.init()
testlog = logging.getLogger('')

class Consumer(multiprocessing.Process):
    
    def __init__(self, id, nc, server='0.0.0.0'):
        multiprocessing.Process.__init__(self)
        self._nc = nc
        self._id = id
        self._server = server

    def set(self, s, key, valsz, data):
        req = 'set %s flags exptime %s\r\n%s\r\n' % (key, valsz, data)
        s.send(req)
        resp = s.recv(40)
        tokens = resp.split('\r\n')
        if tokens[0] != 'STORED':
            testlog.debug(('unable to store data (key = %s)' % (key)))

    def get(self, s, key, valsz, data):
        req = 'get %s\r\n' % (key)
        s.send(req)
        resp = s.recv(valsz + 40)
        tokens = resp.split('\r\n')
        if tokens[0] != ('VALUE %s flags %s' % (key, valsz)):
            testlog.debug(('recieved incorrect data %s' % (resp)))
            testlog.debug(('expecting (%s %s)' % (key, valsz)))

    def run(self):
        proc_name = self.name
        s = socket.socket()
        s.connect((self._server, 11211))
        num = random.randint(1,5)
        testlog.debug(('attempting %d tries' % (self._nc)))
        for i in xrange(self._nc):
            key = 'proc_name_' + str(self._id) + '_' + str(i)
            valsz = random.randint(1,50)
            buf = ''
            for v in xrange(valsz):
                buf = buf + 'i'
            self.set(s, key, valsz, buf)
            self.get(s, key, valsz, buf)
        testlog.debug(('thread %s finished' % (self._id)))
        s.close()
            
if __name__ == '__main__':
    # Start consumers
    if len(sys.argv) < 3:
        testlog.debug('incorrect arguments')
        testlog.debug('python concurrent.py num_threads num_tries <optional server>')
        sys.exit(1)

    num_consumers = int(sys.argv[1])
    num_tries = int(sys.argv[2])
    server = '0.0.0.0'
    if len(sys.argv) > 3:
        server = sys.argv[3]

    print 'Creating %d consumers' % num_consumers
    consumers = [ Consumer(i, num_tries, server=server)
                  for i in xrange(num_consumers) ]
    for w in consumers:
        w.start()
   
    testlog.debug('waiting for threads to complete') 
    for w in consumers:
        w.join()
