import tensorflow as tf
from tensorflow.contrib import rnn


class LayerGenerator:
    def __init__(self):
        pass

    def conv2d(self, input_tensor, output_size, kernel_size, activation, stride, padding,scope='cnn'):
        with tf.variable_scope(scope, reuse=tf.AUTO_REUSE):
            w = tf.get_variable('w', [kernel_size[0], kernel_size[1], input_tensor.get_shape()[-1], output_size])
            b = tf.get_variable('b', [output_size])
        return self.apply_activation(
            tf.nn.conv2d(input_tensor, w, strides=[1, stride[0], stride[1], 1], padding=padding) + b, activation)

    def linear(self, input_tensor, output_size, activation,scope='linear'):
        with tf.variable_scope(scope, reuse=tf.AUTO_REUSE):
            w = tf.get_variable("w", [output_size, input_tensor.get_shape()[-1]], dtype=tf.float32)
            b = tf.get_variable('b', [output_size], dtype=tf.float32)
        return self.apply_activation(tf.matmul(input_tensor, tf.transpose(w)) + b, activation)

    def create_stack_rnn(self, input_tensor, rnn_size, stack_size, using_only_end=False,scope='rnn'):
        with tf.variable_scope(scope, reuse=tf.AUTO_REUSE):
            cell = rnn.MultiRNNCell([self.__create_rnn_cell(rnn_size) for _ in range(stack_size)]
                                    , state_is_tuple=True)
            outputs, _states = tf.nn.dynamic_rnn(cell, input_tensor, dtype=tf.float32)
        if using_only_end is True:
            return outputs[-1]
        return outputs

    def pool(self, input_tensor, pool_type, pool_size, stride_size=None, padding='VALID',scope='pool'):
        with tf.variable_scope(scope, reuse=tf.AUTO_REUSE):
            if stride_size is None:
                stride_size = pool_size

            if pool_type == "AVG":
                return tf.nn.avg_pool(input_tensor, [1, pool_size[0], pool_size[1], 1],
                                      [1, stride_size[0], stride_size[1], 1], padding=padding)
            return tf.nn.max_pool(input_tensor, [1, pool_size[0], pool_size[1], 1],  # default max
                                  [1, stride_size[0], stride_size[1], 1], padding=padding)

    def dropout(self, input_tensor, keep_prob, is_rnn=False):
        if is_rnn is False:
            return tf.layers.dropout(input_tensor, rate=1 - keep_prob, training=False)
        else:
            return rnn.DropoutWrapper(input_tensor, output_keep_prob=keep_prob)

    def __create_rnn_cell(self, rnn_size):
        return rnn.BasicLSTMCell(rnn_size, state_is_tuple=True)

    def apply_activation(self, input_tensor, activation_name):
        if activation_name == "ACTIVATION_NONE":
            return input_tensor
        elif activation_name == "ACTIVATION_RELU":
            return tf.nn.relu(input_tensor)
        elif activation_name == "ACTIVATION_SIGMOID":
            return tf.nn.sigmoid(input_tensor)
        elif activation_name == "ACTIVATION_TANH":
            return tf.nn.tanh(input_tensor)
        elif activation_name == "ACTIVATION_LEAKY_RELU":
            return tf.nn.leaky_relu(input_tensor)
        elif activation_name == "ACTIVATION_ELU":
            return tf.nn.elu(input_tensor)
        else:  # 기본 ReLU
            return tf.nn.relu(input_tensor)
