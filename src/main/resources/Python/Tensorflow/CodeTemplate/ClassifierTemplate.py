import time
import InferenceTemplate
import tensorflow as tf
import numpy as np
from Utils import BatchGenerator

model_path = None
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
max_grad_norm = 20.0


# END_SETTING
def get_paths():
    return x_path, y_path


def get_options():
    return x_option, y_option


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
                (self.inferencer.get_tensor_y() * tf.log(self.logit)) + (1 - self.inferencer.get_tensor_y()) * tf.log(
                    1.0 - self.logit))
            self.predict = tf.cast(self.logit > 0.5, dtype=tf.float32)
            self.accuracy = tf.reduce_mean(
                tf.cast(tf.equal(self.predict, self.inferencer.get_tensor_y()), dtype=tf.float32))
            pass
        elif classifier_option == "SOFTMAX_CLASSIFIER":
            self.loss = tf.reduce_mean(
                tf.nn.softmax_cross_entropy_with_logits(logits=self.logit,
                                                        labels=self.inferencer.get_tensor_y()))
            self.predict = tf.cast(tf.argmax(self.logit, 1), tf.int32)
            self.num_cor = tf.equal(self.predict, tf.cast(tf.argmax(self.inferencer.get_tensor_y(), 1), tf.int32))
            self.accuracy = tf.reduce_mean(tf.cast(self.num_cor, tf.float32))
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

    def training(self, sock, is_training_container):
        customHistory = CustomHistory()
        data_reader = BatchGenerator(self.inferencer.get_data_x(), self.inferencer.get_data_y(), batch_size=batch_size,
                                     validation_percent=validation_ratio)
        with tf.Session() as sess:
            sess.run(tf.global_variables_initializer())
            saver = tf.train.Saver()
            valid_length = int(self.inferencer.get_data_len()*validation_ratio)
            for current_epoch in range(epoch):
                start = time.time()
                for batch_x, batch_y in data_reader.batches():
                    if not is_training_container[0]:
                        #save logic copy
                        import matplotlib.pyplot as plt

                        fig, loss_ax = plt.subplots()

                        acc_ax = loss_ax.twinx()

                        loss_ax.plot(customHistory.train_loss, 'y', label='train loss')

                        acc_ax.plot(customHistory.train_acc, 'b', label='train acc')

                        loss_ax.set_xlabel('step')
                        loss_ax.set_ylabel('loss')
                        acc_ax.set_ylabel('accuray')

                        loss_ax.legend(loc='upper left')
                        acc_ax.legend(loc='lower left')

                        fig.savefig(model_path + "/train.png", format="png")

                        fig, loss_ax = plt.subplots()

                        acc_ax = loss_ax.twinx()
                        acc_ax.plot(customHistory.val_acc, 'g', label='val acc')
                        loss_ax.plot(customHistory.val_loss, 'r', label='val loss')
                        loss_ax.set_xlabel('step')
                        loss_ax.set_ylabel('loss')
                        acc_ax.set_ylabel('accuray')

                        loss_ax.legend(loc='upper left')
                        acc_ax.legend(loc='lower left')

                        fig.savefig(model_path + "/validate.png", format="png")
                        return
                    current_step = tf.train.global_step(sess, self.global_step)
                    feed = {self.inferencer.tensor_x: batch_x, self.inferencer.tensor_y: batch_y,
                            self.inferencer.dropout_keep_prob: 0.7}
                    _, step, loss, accuracy = sess.run(
                        [self.train_op, self.global_step, self.loss, self.accuracy],
                        feed_dict=feed)
                    #print("{}/{} ({} epochs) step, loss : {:.6f}, accuracy : {:.3f}, time/batch : {:.3f}sec"
                    #      .format(current_step, data_reader.num_train / data_reader.batch_size * epoch, current_epoch,
                    #              loss,
                    #              accuracy, time.time() - start))
                    sock.sendall("{}/{} ({} epochs) step, loss : {:.6f}, accuracy : {:.3f}, time/batch : {:.3f}sec_END"
                                 .format(current_step, data_reader.num_train / data_reader.batch_size * epoch,
                                         current_epoch,
                                         loss,
                                         accuracy, time.time() - start).encode())

                    customHistory.train_acc.append(accuracy)
                    customHistory.train_loss.append(loss)
                    if current_step % valid_length == 0:
                        avg_loss, avg_accuracy = 0.0, 0.0
                        start = time.time()
                        for valid_x, valid_y in data_reader.valid_batches():
                            feed = {self.inferencer.tensor_x: valid_x, self.inferencer.tensor_y: valid_y,
                                    self.inferencer.dropout_keep_prob: 0.7}
                            loss, accuracy = sess.run([self.loss, self.accuracy], feed_dict=feed)
                            avg_accuracy += accuracy * len(valid_x)
                            avg_loss += loss * len(valid_x)
                        if len(data_reader.valid_y) != 0:
                            customHistory.val_loss.append(avg_loss / len(data_reader.valid_y))
                            customHistory.val_acc.append(avg_accuracy / len(data_reader.valid_y))
                            #print(
                            #    "({} epochs) evaluation step, loss : {:.6f}, accuracy : {:.3f}, time/batch : {:.3f}sec"
                            #    .format(current_epoch, avg_loss / len(data_reader.valid_y),
                            #            avg_accuracy / len(data_reader.valid_y), time.time() - start))
                            sock.sendall(
                                "({} epochs) evaluation step, loss : {:.6f}, accuracy : {:.3f}, time/batch : {:.3f}sec_END"
                                .format(current_epoch, avg_loss / len(data_reader.valid_y),
                                        avg_accuracy / len(data_reader.valid_y), time.time() - start).encode())
                    start = time.time()
                saver.save(sess, model_path+'/save_model.ckpt')

        import matplotlib.pyplot as plt

        fig, loss_ax = plt.subplots()

        acc_ax = loss_ax.twinx()

        loss_ax.plot(customHistory.train_loss, 'y', label='train loss')

        acc_ax.plot(customHistory.train_acc, 'b', label='train acc')

        loss_ax.set_xlabel('step')
        loss_ax.set_ylabel('loss')
        acc_ax.set_ylabel('accuray')

        loss_ax.legend(loc='upper left')
        acc_ax.legend(loc='lower left')

        fig.savefig(model_path+"/train.png",format="png")

        fig,loss_ax = plt.subplots()

        acc_ax = loss_ax.twinx()
        acc_ax.plot(customHistory.val_acc, 'g', label='val acc')
        loss_ax.plot(customHistory.val_loss, 'r', label='val loss')
        loss_ax.set_xlabel('step')
        loss_ax.set_ylabel('loss')
        acc_ax.set_ylabel('accuray')

        loss_ax.legend(loc='upper left')
        acc_ax.legend(loc='lower left')

        fig.savefig(model_path+"/validate.png", format="png")

    def get_test(self, xPath, yPath):
        self.inferencer = InferenceTemplate.Inferencer(xPath, y_path, x_option, y_option)
        self.logit = self.inferencer.get_logit()
        if classifier_option == "LINEAR_REGRESSION":
            self.predict = self.logit
        elif classifier_option == "SVM":
            raise NotImplementedError("not impl")
        elif classifier_option == "LOGISTIC_CLASSIFIER":
            self.predict = tf.cast(self.logit > 0.5, dtype=tf.float32)
            self.accuracy = tf.reduce_mean(
                tf.cast(tf.equal(self.predict, self.inferencer.get_tensor_y()), dtype=tf.float32))
            pass
        elif classifier_option == "SOFTMAX_CLASSIFIER":
            self.predict = tf.cast(tf.argmax(self.logit, 1), tf.int32)
            self.accuracy = tf.reduce_mean(tf.cast(self.num_cor, tf.float32))
        else:
            raise ValueError("classifier option Error , option = " + str(classifier_option))
        return self.predict, self.accuracy

    def get_try(self, xPath):
        self.inferencer = InferenceTemplate.Inferencer(xPath, y_path, "ENTIRE_VALUE", y_option)
        self.logit = self.inferencer.get_logit()
        if classifier_option == "LINEAR_REGRESSION":
            self.predict = self.logit
        elif classifier_option == "SVM":
            raise NotImplementedError("not impl")
        elif classifier_option == "LOGISTIC_CLASSIFIER":
            self.predict = tf.cast(self.logit > 0.5, dtype=tf.float32)
            pass
        elif classifier_option == "SOFTMAX_CLASSIFIER":
            self.predict = tf.cast(tf.argmax(self.logit, 1), tf.int32)
        else:
            raise ValueError("classifier option Error , option = " + str(classifier_option))
        return self.predict

    def get_classifier_option(self):
        global classifier_option
        return classifier_option

    def get_model_path(self):
        global model_path
        return model_path


class CustomHistory:
    def __init__(self):
        self.train_loss = []
        self.val_loss = []
        self.train_acc = []
        self.val_acc = []
