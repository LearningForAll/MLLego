import time
import InferenceTemplate
import tensorflow as tf
import numpy as np
from Utils import BatchGenerator

x_path = None
y_path = None
x_option = None
y_option = None
classifier_option = None
batch_size = None
epoch = None
learning_rate = None
optimizer = None
validation_ratio = None
max_grad_norm = 5.0


# END_SETTING
class Classifier:
    def __init__(self):
        self.inferencer = InferenceTemplate.Inferencer(x_path, y_path, x_option, y_option)
        self.logit = self.inferencer.get_logit()
        if classifier_option == "LINEAR_REGRESSION":
            self.loss = tf.reduce_mean(tf.square(self.logit - self.inferencer.get_tensor_y()))
            self.predict = self.logit
            self.accuracy = 0  # 정확도 표현불가
        elif classifier_option == "SVM":
            raise NotImplementedError("not impl")
        elif classifier_option == "LOGISTIC_CLASSIFIER":
            self.loss = -tf.reduce_mean(
                self.inferencer.get_tensor_y() * tf.log(self.logit) + ((
                    1 - self.inferencer.get_tensor_y()) * tf.log(1 - self.logit)))
            self.predict = tf.cast(self.logit > 0.5, dtype=tf.float32)
            self.accuracy = tf.reduce_mean(
                tf.cast(tf.equal(self.predict, self.inferencer.get_tensor_y()), dtype=tf.float32))
            pass
        elif classifier_option == "SOFTMAX_CLASSIFIER":
            self.loss = tf.reduce_mean(
                tf.nn.sparse_softmax_cross_entropy_with_logits(logits=self.logit,
                                                               labels=self.inferencer.get_tensor_y()))
            self.predict = tf.nn.softmax(self.logit)
            batch_predictions = np.argmax(self.loss, axis=1)
            num_correct = np.sum(np.equal(batch_predictions, self.inferencer.get_tensor_y()))
            self.accuracy = 100. * num_correct / batch_predictions.shape[0]
        else:
            raise ValueError("classifier option Error , option = " + str(classifier_option))

        self.global_step = tf.Variable(0, name='global_step', trainable=False)
        if optimizer == "OPTIMIZER_GRADIENT_DECENT":
            opt = tf.train.GradientDescentOptimizer(learning_rate)
        elif optimizer == "OPTIMIZER_ADAM":
            opt = tf.train.AdamOptimizer(learning_rate)
        else:
            raise ValueError("Optimizer value error , optimizer = " + str(optimizer))
        tvars = tf.trainable_variables()
        grads, global_norm = tf.clip_by_global_norm(tf.gradients(self.loss, tvars), max_grad_norm)
        self.train_op = opt.apply_gradients(zip(grads, tvars), global_step=self.global_step)

    def training(self):
        data_reader = BatchGenerator(self.inferencer.get_data_x(), self.inferencer.get_data_y(), batch_size=batch_size,
                                     validation_percent=validation_ratio)
        with tf.Session() as sess:
            sess.run(tf.global_variables_initializer())
            for current_epoch in range(epoch):
                start = time.time()
                for batch_x, batch_y in data_reader.batches():
                    current_step = tf.train.global_step(sess, self.global_step)
                    feed = {self.inferencer.tensor_x: batch_x, self.inferencer.tensor_y: batch_y,
                            self.inferencer.dropout_keep_prob: 0.7}
                    _, step, loss, accuracy = sess.run([self.train_op, self.global_step, self.loss, self.accuracy],
                                                       feed_dict=feed)
                    print("{}/{} ({} epochs) step, loss : {:.6f}, accuracy : {:.3f}, time/batch : {:.3f}sec"
                          .format(current_step, data_reader.num_train/data_reader.batch_size * epoch, current_epoch, loss,
                                  accuracy, time.time() - start))
                    start = time.time()
                avg_loss, avg_accuracy = 0.0, 0.0
                start = time.time()
                for valid_x, valid_y in data_reader.valid_batches():
                    feed = {self.inferencer.tensor_x: valid_x, self.inferencer.tensor_y: valid_y,
                            self.inferencer.dropout_keep_prob: 1.0}
                    loss, accuracy = sess.run([self.loss, self.accuracy], feed_dict=feed)
                    avg_accuracy += accuracy * len(valid_x)
                    avg_loss += loss * len(valid_x)
                print("({} epochs) evaluation step, loss : {:.6f}, accuracy : {:.3f}, time/batch : {:.3f}sec"
                      .format(current_epoch, avg_loss / len(data_reader.valid_y),
                              avg_accuracy / len(data_reader.valid_y), time.time() - start))
