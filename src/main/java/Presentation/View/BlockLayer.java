package Presentation.View;

/**
 * Created by LG on 2018-03-30.
 */
import Component.BlockComponent.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

//레이어 블록들이 오는 패널
public class BlockLayer extends JPanel{
    ConvolutionLayerBlock convolutionLayerBlock;
    DenseBlock denseBlock;
    LstmBlock lstmBlock;
    PoolingBlock poolingBlock;

    //Layer 블록들이 오는 패널
    //Convolution, Dense, LSTM, Pooling이 옴
    public BlockLayer(){
        setLayout(null);
        convolutionLayerBlock=new ConvolutionLayerBlock();
        denseBlock=new DenseBlock();
        lstmBlock=new LstmBlock();
        poolingBlock=new PoolingBlock();

        convolutionLayerBlock.setLocation(50,20);
        denseBlock.setLocation(50, denseBlock.getHeight()+70);
        lstmBlock.setLocation(50, denseBlock.getHeight()+lstmBlock.getHeight()+70);
        poolingBlock.setLocation(50, denseBlock.getHeight()+lstmBlock.getHeight()+poolingBlock.getHeight()+95);

        add(convolutionLayerBlock);
        add(denseBlock);
        add(lstmBlock);
        add(poolingBlock);
        setVisible(true);

    }

    public Block[] getBlockComponents() {
        return new Block[]{convolutionLayerBlock,denseBlock,lstmBlock,poolingBlock};
    }
}
