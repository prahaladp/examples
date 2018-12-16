#!/bin/bash
import sys
import socket
import array

HOST = sys.argv[1]  # The server's hostname or IP address
PORT = int(sys.argv[2])       # The port used by the server

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) 
s.connect((HOST, PORT))
myArray = array.array('c', ['\0' for _ in xrange(1024)])
for i in range(0,1023):
    s.sendall(myArray)
s.close()

