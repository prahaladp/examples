import logging
svclog = logging.getLogger('')

class Task(object):
    '''
        Represents task which is queued onto the worker queus
        and eventually consumed by a consumer thread
    '''
    def __init__(self, conn, qindex):
        self._conn = conn
        self._qindex = qindex

    def get_conn(self):
        return self._conn

    def get_queue_index(self):
        return self._qindex
