import multiprocessing
import threading
from Queue import Queue
from memcache_threads import ThreadPool
from task import Task
import logging
svclog = logging.getLogger('')

class MemCacheDb(object):
    '''
        Represents a memcache database - this consists
        of the dictionary which holds the data, thread pool
        to provide concurrent access and this thread pool
        is spread out across multiple clients.

        An operation is submitted to the database by the
        client and the operation is subsequently executed
        on the current data set. See dbop.py for some
        of the operations submitted
    '''
    def __init__(self):
        self._db = {}

        # uses a heuristic based on the current to trigger
        # service/worker threads
        self._nc = multiprocessing.cpu_count() * 2
        self.init_q()
        self._pool = ThreadPool(
                        self._db,
                        self._q
                     )
        self._pool.start()

    def init_q(self):
        self._q = [Queue() for i in xrange(self._nc)]

    def submit(self, cmc):
        svclog.debug('submit')
        q_index = cmc.get_queue_index()
        tq = Task(cmc, q_index)

        self._pool.submit(tq)

    def execute(self, op):
        return op.execute(self._db)

    def get_queue(self):
        return self._pool.assign()

    def wait(self):
        self._q.join()

    def wait_completion(self):
        svclog.debug('wait_completion,inserting kill task')
        for q in self._q:
            q.put(None)

