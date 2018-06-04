import tensorflow as tf
import PreProcessor as pp
import numpy as np
import sys
from socket import *

import ClassifierTemplate

x_path = None
y_path = None
# END_SETTING

serverName = '127.0.0.1'
serverPort = 9999
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))

classifier = ClassifierTemplate.Classifier()

# Predict
if sys.argv[1] == "False":
    test_op = classifier.get_try(x_path)

    sess = tf.Session()
    saver = tf.train.Saver()
    saver.restore(sess, classifier.get_model_path() + '/save_model.ckpt')

    result = sess.run(test_op, feed_dict={classifier.inferencer.tensor_x: classifier.inferencer.get_data_x(),
                                          classifier.inferencer.dropout_keep_prob: 1.0})

    if classifier.get_classifier_option() == "SOFTMAX_CLASSIFIER":
        label_encoder = classifier.inferencer.get_label_encoder()
        result = label_encoder.inverse_transform(result)

    for idx, result_data in enumerate(result):
        # if isinstance(result_data,list) or isinstance(result_data,np.ndarray):
        #    result_data = result_data[0]
        message = "type_test try : " + str(classifier.inferencer.raw_x[idx]) + "  result : " + str(result_data) + "_END"
        clientSocket.sendall(message.encode())
else:
    # Predict + accuracy
    test_op = classifier.get_try(x_path)

    sess = tf.Session()
    saver = tf.train.Saver()
    saver.restore(sess, classifier.get_model_path() + '/save_model.ckpt')

    result = sess.run(test_op, feed_dict={classifier.inferencer.tensor_x: classifier.inferencer.get_data_x(),
                                          classifier.inferencer.dropout_keep_prob: 1.0})

    if classifier.get_classifier_option() == "SOFTMAX_CLASSIFIER":
        label_encoder = classifier.inferencer.get_label_encoder()
        result = label_encoder.inverse_transform(np.argmax(result, axis=1))

    if classifier.get_classifier_option() == "SOFTMAX_CLASSIFIER":
        label_encoder = classifier.inferencer.get_label_encoder()
        result = label_encoder.inverse_transform(np.argmax(result, axis=1))

    for idx, result_data in enumerate(result):
        # if isinstance(result_data,list) or isinstance(result_data,np.ndarray):
        #    result_data = result_data[0]
        message = "type_test try : " + str(classifier.inferencer.get_data_x()[idx]) + "  result : " + str(result_data) + "_END"
        clientSocket.sendall(message.encode())

# message = "acc : "+acc
# clientSocket.sendall(message.encode())
