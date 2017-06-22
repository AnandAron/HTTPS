import socket

HOST, PORT = '', 80

listen_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
listen_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
listen_socket.bind((HOST, PORT))
listen_socket.listen(1)
print 'Serving HTTP on port %s ...' % PORT
while True:
    client_connection, client_address = listen_socket.accept()
    request = client_connection.recv(1024)
    i=1;
    result=''
    while request[-i] != '\n':
        result=result+ request[-i]
        i=i+1
    result=result[::-1]
    print result
    http_response = """\
HTTP/1.1 200 OK

Hello, Guys!
"""
    client_connection.sendall(http_response)
 