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
        perm = np.random.permutation(self.num_data)
        self.train_x = list()
        self.train_y = list()
        self.valid_x = list()
        self.valid_y = list()
        train_perm = perm[:self.num_train]
        valid_perm = perm[self.num_train:]
        for idx in train_perm:
            self.train_x.append(self.X[idx])
            self.train_y.append(self.Y[idx])
        for idx in valid_perm:
            self.valid_x.append(self.X[idx])
            self.valid_y.append(self.Y[idx])

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
