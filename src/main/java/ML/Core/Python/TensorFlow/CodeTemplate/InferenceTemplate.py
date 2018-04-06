import codecs
import LayerGeneartor
import tensorflow

class Inferencer:
    def __init__(self,x_file_path,y_file_path,is_x_folder=False):
        codecs.open(y_file_path, "r", "utf-8")
