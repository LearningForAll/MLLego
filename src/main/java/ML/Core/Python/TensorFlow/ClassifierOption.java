package ML.Core.Python.TensorFlow;

import Const.Classifier;
import Const.FileOption;
import Const.Optimizer;

public class ClassifierOption {
    private String xPath, yPath;
    private FileOption xOption, yOption;
    private Classifier classifier;
    private int batchSize = -1;
    private int epoch = -1;
    private double learning_rate;
    private Optimizer optimizer;
    private double validationRatio;
    private static String[] pythonOptionStrings = new String[]{"x_path", "y_path", "x_option", "y_option", "classifier_option", "batch_size", "epoch", "learning_rate", "optimizer", "validation_ratio"};

    public static String[] getPythonOptionStrings() {
        return pythonOptionStrings;
    }

    public String getxPath() {
        return xPath;
    }

    public void setxPath(String xPath) {
        this.xPath = xPath;
    }

    public String getyPath() {
        return yPath;
    }

    public void setyPath(String yPath) {
        this.yPath = yPath;
    }

    public FileOption getxOption() {
        return xOption;
    }

    public void setxOption(FileOption xOption) {
        this.xOption = xOption;
    }

    public FileOption getyOption() {
        return yOption;
    }

    public void setyOption(FileOption yOption) {
        this.yOption = yOption;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getEpoch() {
        return epoch;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }

    public double getLearning_rate() {
        return learning_rate;
    }

    public void setLearning_rate(double learning_rate) {
        this.learning_rate = learning_rate;
    }

    public Optimizer getOptimizer() {
        return optimizer;
    }

    public void setOptimizer(Optimizer optimizer) {
        this.optimizer = optimizer;
    }

    public double getValidationRatio() {
        return validationRatio;
    }

    public void setValidationRatio(double validationRatio) {
        this.validationRatio = validationRatio;
    }

    public ClassifierOption(String xPath, String yPath, FileOption xOption, FileOption yOption,
                            Classifier classifierOption, int batchSize, int epoch, double learning_rate,
                            Optimizer optimizerOption, double validationRatio) {
        this.xPath = xPath;
        this.yPath = yPath;
        this.xOption = xOption;
        this.yOption = yOption;
        this.classifier = classifierOption;
        this.batchSize = batchSize;
        this.epoch = epoch;
        this.learning_rate = learning_rate;
        this.optimizer = optimizerOption;
        this.validationRatio = validationRatio;
    }

    public String getClassifierOption(String checkOption) {
        switch (checkOption) {
            case "x_path":
                return "\""+xPath+"\"";
            case "y_path":
                return "\""+yPath+"\"";
            case "x_option":
                return "\""+xOption.name()+"\"";
            case "y_option":
                return "\""+yOption.name()+"\"";
            case "classifier_option":
                return "\""+classifier.name()+"\"";
            case "batch_size":
                return "" + batchSize;
            case "epoch":
                return ""+epoch;
            case "learning_rate":
                return ""+learning_rate;
            case "optimizer":
                return "\""+optimizer.name()+"\"";
            case "validation_ratio":
                return ""+validationRatio;
        }
        return "None";
    }
}
