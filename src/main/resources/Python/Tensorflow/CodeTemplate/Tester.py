import tensorflow as tf
import PreProcessor as pp
import numpy as np
from socket import *

import ClassifierTemplate

x_path = None
# END_SETTING

serverName = '127.0.0.1'
serverPort = 9999
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))

sess = tf.Session()
saver = tf.train.Saver()
saver.restore(sess, './save_model.ckpt')

classifier = ClassifierTemplate.Classifier()
test_op = classifier.get_test(x_path)

result, acc = sess.run(test_op, feed_dict={classifier.inferencer.tensor_x: classifier.inferencer.get_data_x(),
                                           classifier.inferencer.dropout_keep_prob: 1.0})

if classifier.get_classifier_option() != "LINEAR_REGRESSION":
    label_encoder = classifier.inferencer.get_label_encoder()
    result = label_encoder.inverse_transform(np.argmax(result, axis=1))

for result_data in result:
    message = "result : " + result+"_END"
    clientSocket.sendall(message.encode())

message = "acc : "+acc
clientSocket.sendall(message.encode())

