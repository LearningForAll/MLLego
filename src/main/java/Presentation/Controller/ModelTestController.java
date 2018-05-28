package Presentation.Controller;

import Component.BlockComponent.Block;
import Component.BlockObserver.TestBlockObserver;
import Component.TestBlockComponent.TestBlock;
import Presentation.View.ModelTestPanel;

import java.util.ArrayList;
import java.util.List;

public class ModelTestController implements TestBlockObserver {

    // singleton pattern
    private static ModelTestController instance = new ModelTestController();
    private List<TestBlock> blocks;
    static boolean isBlockEventEvoked = false;
    // 패널을 가지고 있음 컨트롤러니까
    ModelTestPanel panel;


    private ModelTestController() {
        blocks = new ArrayList<>();
    }

    public static ModelTestController getInstance() {
        return instance;
    }

    public void setPanel(ModelTestPanel panel) {
        this.panel = panel;
    }

    public void addTestBlock(TestBlock block){
        blocks.add(block);
        // 옵저버 등록
        block.setObserver(this);
        //항상 스택처럼 쌓일테니까 마지막블록에 등록해줌
        panel.addTestBlock(block);

    }
    public void deleteTestBlock(TestBlock block){
        blocks.remove(block);
    }

    @Override
    public void blinkBlock(Block block) {

    }

    @Override
    public void revertOrConnectBlock(Block block) {

    }
}
