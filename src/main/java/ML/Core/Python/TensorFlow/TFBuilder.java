package ML.Core.Python.TensorFlow;

import Component.BlockComponent.ModelBlock;
import Component.BlockComponent.TrainingBlock;
import ML.Core.MLBuilder;
import ML.Core.ProcessListener;

public class TFBuilder implements MLBuilder {
    ProcessListener processListener = null;
    @Override
    public void setProcessListener(ProcessListener processListener) {

    }

    @Override
    public boolean generateCodeFile(TrainingBlock trainingBlock) {
        return false;
    }

    @Override
    public boolean training() {
        return false;
    }
}
