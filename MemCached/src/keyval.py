import logging
svclog = logging.getLogger('')

class KeyVal(object):
    '''
        Represents a basic key value and additional attributes required
        by memcachedb - note this is a basic representation and most
        of the fields are stored as strings (time, sz etc) and manipulated
        as required
    '''
    def __init__(self, key, flags, exptime, sz, val):
        self._key = key
        self._flags = flags
        self._exptime = exptime
        self._sz = sz
        self._val = val

    def get_key(self):
        return self._key

    def get_flags(self):
        return self._flags

    def get_sz(self):
        return self._sz

    def get_val(self):
        return self._val

    def get_exptime(self):
        return self._exptime

