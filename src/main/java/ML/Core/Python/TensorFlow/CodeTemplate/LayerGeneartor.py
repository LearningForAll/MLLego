import tensorflow as tf
import numpy as np


class LayerGenerator:

    def conv2d(self, input_tensor, output_size, kernel_size, activation, stride, padding):
        w = tf.get_variable('w', [kernel_size[0], kernel_size[1], input_tensor.get_shape()[-1], output_size])
        b = tf.get_variable('b', [output_size])
        if activation == "ACTIVATION_NONE":
            return tf.nn.conv2d(input_tensor, w, strides=[1, stride[0], stride[1], 1], padding=padding) + b
        elif activation == "ACTIVATION_RELU":
            return tf.nn.relu(tf.nn.conv2d(input_tensor, w, strides=[1, stride[0], stride[1], 1], padding=padding) + b)
        elif activation == "ACTIVATION_SIGMOID":
            return tf.nn.sigmoid(
                tf.nn.conv2d(input_tensor, w, strides=[1, stride[0], stride[1], 1], padding=padding) + b)
        elif activation == "ACTIVATION_TANH":
            return tf.nn.tanh(tf.nn.conv2d(input_tensor, w, strides=[1, stride[0], stride[1], 1], padding=padding) + b)
        elif activation == "ACTIVATION_LEAKY_RELU":
            return tf.nn.leaky_relu(
                tf.nn.conv2d(input_tensor, w, strides=[1, stride[0], stride[1], 1], padding=padding) + b)
        elif activation == "ACTIVATION_ELU":
            return tf.nn.elu(tf.nn.conv2d(input_tensor, w, strides=[1, stride[0], stride[1], 1], padding=padding) + b)
        else:  # basic = ReLU
            return tf.nn.relu(tf.nn.conv2d(input_tensor, w, strides=[1, stride[0], stride[1], 1], padding=padding) + b)

    def linear(self, input_tensor, output_size):
        w = tf.get_variable("w", [output_size, input_tensor.get_shape()[-1]], dtype=tf.float32)
        b = tf.get_variable('b', [output_size], dtype=tf.float32)
        return tf.matmul(input_tensor, tf.transpose(w)) + b
    tf.layers.conv2d()
    def create_rnn_cell(self, rnn_size):

        return tf.contrib.rnn.BasicLSTMCell(rnn_size, state_is_tuple=True, forget_bias=0.0)