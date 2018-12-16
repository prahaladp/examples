#!/usr/bin/python

import socket

HOST = '0.0.0.0'  # Standard loopback interface address (localhost)
PORT = 64000   # Port to listen on (non-privileged ports are > 1023)

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind((HOST, PORT))

while 1:
    print ('accepting')
    while 1:
        print ('receiving data')
        data = s.recv(1024)
        if not data:
            break
    break
