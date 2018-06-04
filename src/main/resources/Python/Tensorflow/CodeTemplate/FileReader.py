import os


def read_file(x_file_path, y_file_path, x_using_option="ALL", y_using_option="ALL"):
    is_x_dir = os.path.isdir(x_file_path)
    is_y_dir = os.path.isdir(y_file_path)
    data_x_list = list()
    data_y_list = list()
    if is_x_dir is True:
        for path, directory, files in os.walk(x_file_path):
            for filename in files:
                data_x_list.append("%s/%s" % (path, filename))
    else:
        text_file = open(x_file_path, 'r')
        file_lines = text_file.read().splitlines()
        for line in file_lines:
            vect_element = line.split(",")
            vect = list()
            if x_using_option == "ENTIRE_VALUE":
                for element in vect_element:
                    vect.append(element)
            elif x_using_option == "REMOVE_THE_END":
                for element in vect_element[:-1]:
                    vect.append(element)
            elif x_using_option == "REMOVE_THE_START":
                for element in vect_element[1:]:
                    vect.append(element)
            elif x_using_option == "EXCEPT_FIRST_END":
                for element in vect_element[1:-1]:
                    vect.append(element)
            else:  # default : ALL
                for element in vect_element:
                    vect.append(element)
            data_x_list.append(vect)

    if is_y_dir is True:
        for path, directory, files in os.walk(y_file_path):
            name = path.split("\\")[-1]
            for _ in range(len(files)):
                data_y_list.append(name)
    else:
        text_file = open(y_file_path, 'r')
        file_lines = text_file.read().splitlines()
        for line in file_lines:
            vect_element = line.split(",")
            vect = list()
            if y_using_option == "ENTIRE_VALUE":
                for element in vect_element:
                    vect.append(element)
            elif y_using_option == "ONLY_END":
                vect.append(vect_element[-1])
            elif y_using_option == "ONLY_FIRST":
                vect.append(vect_element[0])
            else:  # default : ALL
                for element in vect_element:
                    vect.append(element)
            data_y_list.append(vect)
    return data_x_list, data_y_list
