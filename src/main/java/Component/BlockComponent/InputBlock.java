package Component.BlockComponent;

import Component.BlockBatchModel.BlockTemplateComponent.InputBlockTemplate;
import Const.FileType;
import Const.InputOption;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import Const.FileType;
import Const.InputOption;

public abstract class InputBlock extends Block {
    public abstract FileType getFileType();

    public abstract InputOption getInputOption();

    @Override
    public boolean isNextBlockConnectable(Block block) {
        // 블록이 연결되어 있을 경우
        return (block instanceof PreprocessorBlock || block instanceof LayerBlock || block instanceof ClassifierBlock);

    }

    //시작 블록
    @Override
    public boolean isPreviousBlockConnectable(Block block) {
        return false;
    }

    @Override
    public boolean isNextBlockConnected() {
        return (nextBlocks.size() != 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {

        // 시작블록이지만 전 블록이랑 연결된 것은 아니기 때문에 로직 변경
        return true;
    }

}