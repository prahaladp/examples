import socket
import log
import logging

log.init()
testlog = logging.getLogger('')

def testcase1():
    testlog.debug('test case 1')
    testlog.debug('test - add key')
    req = 'set key2 flags exptime 10\r\n1234567890\r\n'
    s.send(req)
    resp = s.recv(100)
    # parse the response
    testlog.debug(('received response %s' % (resp)))
    tokens = resp.split('\r\n')
    if tokens[0] != 'STORED':
        testlog.debug('test case 1 failed')
        return

    testlog.debug('test case 1 succeeded')

def testcase2():
    testlog.debug('test case 2')
    testlog.debug('retrieving key')
    req = 'get key2\r\n'
    s.send(req)
    resp = s.recv(50)
    tokens = resp.split('\r\n')
    if tokens[0] != 'VALUE key2 flags 10':
        testlog.debug('test case 2 failed')
        testlog.debug(('recieved incorrect data %s' % (resp)))
        return
    if tokens[1] != '1234567890':
        testlog.debug('test case 2 failed')
        testlog.debug(('recieved incorrect data %s' % (resp)))
        return

    testlog.debug('test case 2 succeeded') 

def testcase3():
    testlog.debug('test case 3')
    testlog.debug('update the same key again')
    val = ''
    for i in xrange(20):
        val = val + 'i'
    req = 'set key2 flags exptime 20\r\n' + val + '\r\n'
    s.send(req)

    resp = s.recv(100)
    # parse the response
    testlog.debug(('received response %s' % (resp)))
    tokens = resp.split('\r\n')
    if tokens[0] != 'STORED':
        testlog.debug('test case 3 failed')
        return

    testlog.debug('test case 3 succeeded')

def testcase4():
    testlog.debug('test case 4')
    testlog.debug('read the updated key')
    req = 'get key2\r\n'
    s.send(req)
    resp = s.recv(50)
    val = ''
    for i in xrange(20):
        val = val + 'i'
    tokens = resp.split('\r\n')
    if tokens[0] != 'VALUE key2 flags 20':
        testlog.debug('test case 4 failed')
        testlog.debug(('recieved incorrect data %s' % (resp)))
        return
    if tokens[1] != val:
        testlog.debug('test case 4 failed')
        testlog.debug(('recieved incorrect data %s' % (resp)))
        return

    testlog.debug('test case 4 succeeded') 

def testcase5():
    testlog.debug('test case 4')
    testlog.debug('large key update')
    val = ''
    for i in xrange(20000):
        val = val + 'i'
    req = 'set key2 flags exptime 20000\r\n' + val + '\r\n'
    s.send(req)
    resp = s.recv(100)
    # parse the response
    testlog.debug(('received response %s' % (resp)))
    tokens = resp.split('\r\n')
    if tokens[0] != 'STORED':
        testlog.debug('test case 5 failed')
        return

    testlog.debug('test case 5 large key update succeeded')

def testcase6():
    testlog.debug('test case 6')
    testlog.debug('read the large key')
    req = 'get key2\r\n'
    s.send(req)
    resp = s.recv(64000)
    val = ''
    for i in xrange(20000):
        val = val + 'i'
    tokens = resp.split('\r\n')
    testlog.debug(('large key recieved = %d'  % (len(resp))))
    if tokens[0] != 'VALUE key2 flags 20000':
        testlog.debug('test case 6 failed')
        testlog.debug(('recieved incorrect data %s' % (resp)))
        return
    if tokens[1] != val:
        testlog.debug('test case 6 failed')
        testlog.debug(('recieved incorrect data %s' % (resp)))
        return

    testlog.debug('test case 6 succeeded')

# NOTE
# This test case doesn;t work, since it is unstructured and hence it is not supported
# The unstructured data stream might mix with the regular delimiters and hence would
# require additional handling on the server side, this is beyond the scope of the
# requirements.
def testcase7():
    testlog.debug('test case 7')
    testlog.debug('get and set on unstructured byte stream')
    ubuf = ''
    for i in xrange(1000):
        ubuf = ubuf + chr(i % 256)
    testlog.debug(('ubuf = ', ubuf))
    req = 'set key2 flags exptime 1000\r\n' + ubuf + '\r\n'
    s.send(req)
    resp = s.recv(100)
    testlog.debug(('received response %s' % (resp)))
    tokens = resp.split('\r\n')
    if tokens[0] != 'STORED':
        testlog.debug('test case 7 failed')
        return

    testlog.debug('reading key now')
    req = 'get key2\r\n'
    s.send(req)
    resp = s.recv(64000)
    tokens = resp.split('\r\n')
    if tokens[0] != 'VALUE key2 flags 10000':
        testlog.debug('test case 7 failed')
        return
    if tokens[1] != ubuf:
        testlog.debug('test case 7 failed, unexpected data buffer')
        return

    testlog.debug('test case 7 succeeded')

if __name__ == "__main__":
    s = socket.socket()
    s.connect(('0.0.0.0', 11211))
    testcase1()
    testcase2()
    testcase3()
    testcase4()
    testcase5()
    testcase6()

    #testcase7()
    s.close()
