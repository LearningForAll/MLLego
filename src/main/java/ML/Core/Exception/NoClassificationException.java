package ML.Core.Exception;

import Component.BlockComponent.TrainingBlock;

public class NoClassificationException extends IncompleteBlockException {
    TrainingBlock trainingBlock;
    public NoClassificationException() {}
    public NoClassificationException(String msg) {
        super(msg);
    }
    public NoClassificationException(String msg,TrainingBlock trainingBlock){
        super(msg);
        this.trainingBlock = trainingBlock;
    }
    public String getMessage() {
        String superMessage = super.getMessage();
        String noClassificationMessage = null;
        if (trainingBlock!=null) noClassificationMessage = trainingBlock.toString();

        if (superMessage == null) {
            return noClassificationMessage;
        } else if (noClassificationMessage == null) {
            return superMessage;
        } else {
            return superMessage + noClassificationMessage;
        }
    }
}
