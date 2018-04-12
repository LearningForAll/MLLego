import codecs
from sklearn import preprocessing
import numpy as np
import os
import LayerGeneartor as lg
import PreProcessor as pp
import FileReader as fr


class Inferencer:
    def __init__(self, x_file_path, y_file_path, x_using_option="ALL", y_using_option="ALL"):
        self.raw_x, self.raw_y = fr.read_file(x_file_path, y_file_path, x_using_option=x_using_option,
                                              y_using_option=y_using_option)
        self.tensor_x = None
        self.tensor_y = None
        self.data_x = self.raw_x  #  raw_x를 가공하면 data_x에 저장
        self.data_y = self.raw_y  #  raw_y를 가공하면 data_y에 저장
        self.is_training = False

    def get_tensor_x(self):
        return self.tensor_x

    def get_tensor_y(self):
        return self.tensor_y

    def get_data_x(self):
        return self.data_x

    def get_data_y(self):
        return self.data_y

    def get_logit(self):
        # below here, MLLego will generate source code.
        pass
