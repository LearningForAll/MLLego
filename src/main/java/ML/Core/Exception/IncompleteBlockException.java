package ML.Core.Exception;

import Component.BlockComponent.InputBlock;
import Component.BlockComponent.TrainingBlock;

public class IncompleteBlockException extends IllegalStateException {
    TrainingBlock trainingBlock;
    InputBlock[] inputBlocks;
    public IncompleteBlockException() {}
    public IncompleteBlockException(String msg) {
        super(msg);
    }
    public IncompleteBlockException(String msg, TrainingBlock trainingBlock){
        super(msg);
        this.trainingBlock = trainingBlock;
    }
    public String getMessage() {
        String superMessage = super.getMessage();
        inputBlocks = getInputBlocks(trainingBlock);

        String resultMessage = "";
        if (superMessage!= null)resultMessage+=superMessage;
        if (inputBlocks.length<2){
            resultMessage+=", 인풋 블록 개수가 부족합니다.";
        }
        return resultMessage;
    }

    private InputBlock[] getInputBlocks(TrainingBlock trainingBlock) {
        //TODO: 만들어야함 지금 Mock 함수임
        return new InputBlock[2];
    }
}
