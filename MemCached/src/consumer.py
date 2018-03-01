from threading import Thread
import logging
svclog = logging.getLogger('')

class ConsumerThread(Thread):
    '''
        This is an instance of a worker thread which consumes
        the requests which are queued up on this thread.
        Each item on the queue represents a Task which has the
        information on the operations to be performed
        including the client connection, the protocol buffer etc.
        The thread then submits and sends the response back to
        the client
    '''
    def __init__(self, id, q):
        Thread.__init__(self)
        self._id = id
        self._q = q

    def run(self):
        svclog.debug(('thread %d running' % (self._id)))
        while True:
            task = self._q.get()
            if task is None:
                # need to break
                svclog.debug(('received termination signal, break %d' % (self._id)))
                break

            svclog.debug(('(thread %d) executing task' % (self._id)))

            conn = task.get_conn()
            conn.lock()
            db = conn.get_db()
            ops = conn.get_protocol_buffer().parse()
            conn.release()
            res = [db.execute(db_op) for db_op in ops]
            for r in res:
                conn.send_response(r)
            svclog.debug(('(thread %d) task done' % (self._id)))
