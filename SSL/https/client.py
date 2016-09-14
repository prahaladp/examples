import xmlrpclib

print 'Sleeping'
import time
#time.sleep(60)
print 'Connecting'
server = xmlrpclib.Server('https://localhost:9090')
print server.add(1,2)
print server.div(10,4)

