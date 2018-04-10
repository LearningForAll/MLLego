import numpy as np


class BatchGenerator:
    def __init__(self, x, y, batch_size, validation_percent=0.1):
        self.X = x
        self.Y = y
        self.batch_size = batch_size
        self.validation_percent=validation_percent
        self.num_data = len(y)
        self.num_train = int(self.num_data * (1 - validation_percent))
        self.num_valid = self.num_data - self.num_train
        self.train_x = self.X[:self.num_train]
        self.train_y = self.Y[:self.num_train]
        self.valid_x = self.X[self.num_train:]
        self.valid_y = self.Y[self.num_train:]

    def batches(self):
        perm = np.random.permutation(self.num_train)
        batch_x, batch_y = list(), list()
        for idx in perm:
            batch_x.append(self.train_x[idx])
            batch_y.append(self.train_y[idx])
            if len(batch_x) >= self.batch_size:
                yield batch_x, batch_y
                batch_x.clear()
                batch_y.clear()
        if len(batch_x) > 0:
            yield batch_x, batch_y

    def valid_batches(self):
        perm = np.random.permutation(self.num_valid)
        batch_x, batch_y = list(), list()
        for idx in perm:
            batch_x.append(self.valid_x[idx])
            batch_y.append(self.valid_y[idx])
            if len(batch_x) >= self.batch_size:
                yield batch_x, batch_y
                batch_x.clear()
                batch_y.clear()
        if len(batch_x) > 0:
            yield batch_x, batch_y
