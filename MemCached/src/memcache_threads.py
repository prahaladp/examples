from consumer import ConsumerThread
from lb import LoadBalancer
import logging
svclog = logging.getLogger('')

class ThreadPool(object):
    '''
        ThreadPool object which takes as a parameter a list of queue objects,
        the worker threads are created at startup and assigned a queue. The clients
        are then assigned by the load balancer and the threads pick up the
        tasks and run with it
    '''
    def __init__(self, db, qlist):
        self._qlist = qlist
        self._db = db
        self._consumers = [
            ConsumerThread(
                i,
                self._qlist[i]
            ) for i in xrange(len(qlist))
        ]
        self._lb = LoadBalancer(len(qlist))

    def start(self):
        for c in self._consumers:
            c.start()

    def submit(self, tq):
        self._qlist[tq.get_queue_index()].put(tq)

    def assign(self):
        return self._lb.assign()
