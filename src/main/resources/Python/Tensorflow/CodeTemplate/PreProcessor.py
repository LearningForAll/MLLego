from scipy.misc import pilutil
import numpy as np
from sklearn import preprocessing
import tensorflow as tf


def image_to_vector(source, option="RGB", size=None):
    if size is None:
        size = [28, 28]
    if option == "BW":
        option = "L"
    if isinstance(source, str):
        img = pilutil.imread(source, mode=option)  # shape: (H, W, 3), range: [0, 255]
        img = pilutil.imresize(img, (size[0], [size[1]]), mode=option).astype(np.float32)
        return img
    result_list = list()
    for element in source:  # TODO : 대용량 이미지 또는 엄청 여러개의 이미지인경우는 메모리 상황 고려 소스 작성
        img = pilutil.imread(element, mode=option)  # shape: (H, W, 3), range: [0, 255]
        img = pilutil.imresize(img, (size[0], size[1]), mode=option).astype(np.float32)
        if option == "L":
            img = np.reshape(img, [size[0], size[1], 1])
        result_list.append(img)
    return result_list


def one_hot_encoding(source):
    label_encoder = preprocessing.LabelEncoder()
    onehot_encoder = preprocessing.OneHotEncoder()
    label_result = label_encoder.fit_transform(source)
    onehot_result = onehot_encoder.fit_transform(label_result.reshape(-1, 1)).toarray()
    return onehot_result


def dimension_reduction():
    pass


def make_placeholder(data):
    np_data = np.array(data)
    param = [s for s in np.shape(np_data)[1:]]
    return tf.placeholder(tf.float32, param)


def make_placeholder_with_batch_space(data):
    np_data = np.array(data)
    param = [s for s in np.shape(np_data)[1:]]
    param.insert(0, None)
    return tf.placeholder(tf.float32, param)

