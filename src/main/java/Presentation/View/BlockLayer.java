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
        denseBlock=new DenseBlock(2);
        lstmBlock=new LstmBlock();
        poolingBlock=new PoolingBlock();

        convolutionLayerBlock.setSize(200,90);
        convolutionLayerBlock.setLocation(150,20);
        denseBlock.setSize(200,50);
        denseBlock.setLocation(150, 145);
        lstmBlock.setSize(200,50);
        lstmBlock.setLocation(150, 245);
        poolingBlock.setSize(200,75);
        poolingBlock.setLocation(150, 335);

        add(convolutionLayerBlock);
        add(denseBlock);
        add(lstmBlock);
        add(poolingBlock);
        setVisible(true);
    }

}
