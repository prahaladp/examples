import logging

class ServiceLog(object):
    '''
        Enables logging
    '''
    def __init__(self):
        logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    filename='service.log',
                    filemode='a')
        ch = logging.StreamHandler()
        formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        ch.setFormatter(formatter)
        ch.setLevel(logging.DEBUG)
        logging.getLogger('').addHandler(ch)

def init():
    global svclog
    svclog = ServiceLog()
