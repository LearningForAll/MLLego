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
        if(previousBlocks.size() > 0){
            return false;
        }else{
            return (block instanceof TrainingBlock);
        }
    }
}
