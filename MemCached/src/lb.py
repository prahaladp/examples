import threading

class LoadBalancer(object):
    '''
        Simple round robin load balancers which assigns the
        the queues to a client connection based on the count
        of the number of worker threads which are created during
        startup
    '''
    def __init__(self, num):
        # number of queues
        self._num = num
        self._index = 0
        self._lock = threading.Lock()

    def assign(self):
        self._lock.acquire()
        next_index = self._index
        if self._index + 1 < self._num:
            self._index = self._index + 1
        else:
            self._index = 0
        self._lock.release()

        return next_index

