package Presentation.Controller;

import Component.BlockComponent.Block;
import Component.BlockObserver.TestBlockObserver;
import Component.TestBlockComponent.TestBlock;
import Component.TestBlockComponent.TestModelBlock;
import Presentation.View.ModelTestPanel;
import Presentation.View.ModelTestResultPanel;

import java.util.ArrayList;
import java.util.List;

public class ModelTestController implements TestBlockObserver {

    // singleton pattern
    private static ModelTestController instance = new ModelTestController();
    private List<TestBlock> blocks;
    static boolean isBlockEventEvoked = false;
    // 패널을 가지고 있음 컨트롤러니까
    ModelTestPanel panel;
    ModelTestResultPanel resultPanel;

    private ModelTestController() {
        blocks = new ArrayList<>();
    }

    public static ModelTestController getInstance() {
        return instance;
    }

    public void setPanel(ModelTestPanel panel) {
        this.panel = panel;
    }

    public void setResultPanel(ModelTestResultPanel panel){
        this.resultPanel = panel;
    }

    public void addTestModel(String modelName){
        TestModelBlock testModelBlock = new TestModelBlock(modelName);
        blocks.add(testModelBlock);
        // 옵저버 등록
        testModelBlock.setObserver(this);
        //항상 스택처럼 쌓일테니까 마지막블록에 등록해줌
        panel.addModelBlock(testModelBlock);

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

    public String getSelectedModelName(){
        return panel.getTestModelBlock().getModelName();
    }

    public String getSelectedDataPath(){
        return panel.getInputBlock().getXPath();
    }

    public void addResultLine(String result){
        resultPanel.addLine(result);
    }
}
