package ML.Core.Python.TensorFlow;

import Const.Classifier;
import Const.Optimizer;

public class ClassifierOption {
    private String modelPath;
    private String xPath, yPath;
    private String xOption, yOption;
    private Classifier classifier;
    private int batchSize = -1;
    private int epoch = -1;
    private float learning_rate;
    private Optimizer optimizer;
    private float validationRatio;
    private static String[] pythonOptionStrings = new String[]{"model_path","x_path", "y_path", "x_option", "y_option", "classifier_option", "batch_size", "epoch", "learning_rate", "optimizer", "validation_ratio"};

    public static String[] getPythonOptionStrings() {
        return pythonOptionStrings;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
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

    public String getxOption() {
        return xOption;
    }

    public void setxOption(String xOption) {
        this.xOption = xOption;
    }

    public String getyOption() {
        return yOption;
    }

    public void setyOption(String yOption) {
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

    public float getLearning_rate() {
        return learning_rate;
    }

    public void setLearning_rate(float learning_rate) {
        this.learning_rate = learning_rate;
    }

    public Optimizer getOptimizer() {
        return optimizer;
    }

    public void setOptimizer(Optimizer optimizer) {
        this.optimizer = optimizer;
    }

    public float getValidationRatio() {
        return validationRatio;
    }

    public void setValidationRatio(float validationRatio) {
        this.validationRatio = validationRatio;
    }

    public ClassifierOption(String modelPath,String xPath, String yPath, String xOption, String yOption,
                            Classifier classifierOption, int batchSize, int epoch, float learning_rate,
                            Optimizer optimizerOption, float validationRatio) {
        this.modelPath = modelPath;
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
        String slashPath;
        switch (checkOption) {
            case "model_path":
                slashPath = modelPath.replaceAll("\\\\","/");
                return "\""+slashPath+"\"";
            case "x_path":
                slashPath = xPath.replaceAll("\\\\","/");
                return "\""+slashPath+"\"";
            case "y_path":slashPath = yPath.replaceAll("\\\\","/");
                return "\""+slashPath+"\"";
            case "x_option":
                return "\""+xOption+"\"";
            case "y_option":
                return "\""+yOption+"\"";
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
