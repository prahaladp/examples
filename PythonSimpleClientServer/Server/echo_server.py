#!/usr/bin/python

import socket
import sys

HOST = '0.0.0.0'  # Standard loopback interface address (localhost)
PORT = 64000   # Port to listen on (non-privileged ports are > 1023)

if len(sys.argv) > 1:
    PORT = int(sys.argv[1])

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
s.listen(5)

while 1:
    print ('accepting')
    conn, addr = s.accept()
    while 1:
        print('Connected by', addr)
        data = conn.recv(1024)
        if not data:
            break
        conn.sendall(data)
