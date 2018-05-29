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
        setBackground(Color.white);

        convolutionLayerBlock=new ConvolutionLayerBlock();
        denseBlock=new DenseBlock();
        lstmBlock=new LstmBlock();
        poolingBlock=new PoolingBlock();

        convolutionLayerBlock.setLocation(50,30);
        denseBlock.setLocation(50, convolutionLayerBlock.getHeight()+80);
        lstmBlock.setLocation(50, denseBlock.getHeight()+convolutionLayerBlock.getHeight()+130);
        poolingBlock.setLocation(50, denseBlock.getHeight()+lstmBlock.getHeight()+convolutionLayerBlock.getHeight()+180);

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
