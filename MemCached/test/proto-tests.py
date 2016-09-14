import socket
import log
import logging

log.init()
testlog = logging.getLogger('')

def testcase1():
    testlog.debug('test case 1')
    testlog.debug('test - set key')
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
    testlog.debug('retrieving key with no key specified')
    req = 'get\r\n'
    s.send(req)
    resp = s.recv(50)
    tokens = resp.split('\r\n')
    if tokens[0] != 'END':
        testlog.debug('test case 2 failed')
        testlog.debug(('expecting to receive END, received ', resp))
        return

    testlog.debug('test case 2 succeeded') 

def testcase3():
    testlog.debug('test case 3')
    testlog.debug('set with incorrect parameters, missing flags and exp-time')
    req = 'set key2 10\r\n1234567890\r\n'
    s.send(req)
    resp = s.recv(100)
    testlog.debug(('received response %s' % (resp)))
    tokens = resp.split('\r\n')
    if tokens[0] != 'NOT_STORED':
        testlog.debug('test case 3 failed')
        return

    testlog.debug('test case 3 succeeded')

def testcase4():
    testlog.debug('test case 4')
    testlog.debug('set with incorrect data block size')
    req = 'set key2 flags exptime 5\r\n1234567890\r\n'
    s.send(req)
    resp = s.recv(50)
    tokens = resp.split('\r\n')
    if tokens[0] != 'NOT_STORED':
        testlog.debug('test case 4 failed')
        testlog.debug(('recieved incorrect response %s' % (resp)))
        return

    testlog.debug('test case 4 succeeded') 

def testcase5():
    testlog.debug('test case 5')
    testlog.debug('get with multiple keys')
    req = 'get key2 key3\r\n'
    s.send(req)
    resp = s.recv(100)
    testlog.debug(('received response %s' % (resp)))
    tokens = resp.split('\r\n')
    get_resp = tokens[0].split(' ')
    if get_resp[0] != 'VALUE':
        testlog.debug('test case 5 failed')
        testlog.debug(('recieved incorrect response %s' % (resp)))
        return

    testlog.debug('test case 5 succeeded')

if __name__ == "__main__":
    s = socket.socket()
    s.connect(('0.0.0.0', 11211))
    testcase1()
    testcase2()
    testcase3()
    testcase4()
    testcase5()
    s.close()

