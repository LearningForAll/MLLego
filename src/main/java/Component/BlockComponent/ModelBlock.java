package Component.BlockComponent;

import Models.Coords;

public class ModelBlock extends Block {

    @Override
    String getBlockAttrStr() {
        return null;
    }


    @Override
    boolean isNextBlockConnectable(Block block) {
        // 최종 Output 블록
        return false;
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        return (block instanceof TrainingBlock);
    }
}
