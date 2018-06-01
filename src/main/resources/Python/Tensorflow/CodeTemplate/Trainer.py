from time import sleep

import ClassifierTemplate
import threading
from socket import *

isTraining = False


def main():
    global isTraining
    serverName = '127.0.0.1'
    serverPort = 9999
    clientSocket = socket(AF_INET, SOCK_STREAM)
    clientSocket.connect((serverName, serverPort))

    classifier = ClassifierTemplate.Classifier()
    recvThread = threading.Thread(target=read_thread, args=[clientSocket], daemon=True)
    isTraining = True
    recvThread.start()
    classifier.training(clientSocket, [isTraining])

def read_thread(sock):
    global isTraining
    while isTraining:
        data = sock.recv(1024)
        if not data:
            isTraining = False
            sock.close()
            return
        m = data.decode()
        if m == "stop":
            isTraining = False
            sock.send("Training is stopped".encode())
            sock.close()
            return


if __name__ == "__main__":
    main()
