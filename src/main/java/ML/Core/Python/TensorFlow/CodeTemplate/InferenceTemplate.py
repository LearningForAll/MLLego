import codecs
from sklearn import preprocessing
import numpy as np
import os
import LayerGeneartor as lg
import PreProcessor as pp
import FileReader as fr


class Inferencer:
    def __init__(self,x_file_path, y_file_path, x_using_option="ALL", y_using_option="ALL"):
        self.X , self.Y = fr.read_file(x_file_path, y_file_path, x_using_option=x_using_option, y_using_option=y_using_option)

    def get_logit(self):
        # below here, MLLego will generate source code.
        pass
