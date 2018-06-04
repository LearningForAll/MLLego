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
public class BlockLayer extends JScrollPane{
    ConvolutionLayerBlock convolutionLayerBlock;
    DenseBlock denseBlock;
    LstmBlock lstmBlock;
    PoolingBlock poolingBlock;
    JPanel jpanel=new JPanel(null);

    //Layer 블록들이 오는 패널
    //Convolution, Dense, LSTM, Pooling이 옴
    public BlockLayer(){
        super(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        int location=30;
        setBackground(Color.white);
        setPreferredSize(new Dimension(300,800));
        jpanel.setPreferredSize(new Dimension(300,1000));
        jpanel.setBackground(Color.white);

        convolutionLayerBlock=new ConvolutionLayerBlock();
        denseBlock=new DenseBlock();
        lstmBlock=new LstmBlock();
        poolingBlock=new PoolingBlock();

        convolutionLayerBlock.setLocation(50,location);
        location+=50+convolutionLayerBlock.getHeight();
        denseBlock.setLocation(50, location);
        location+=50+denseBlock.getHeight();
        lstmBlock.setLocation(50, location);
        location+=50+lstmBlock.getHeight();
        poolingBlock.setLocation(50, location);

        jpanel.add(convolutionLayerBlock);
        jpanel.add(denseBlock);
        jpanel.add(lstmBlock);
        jpanel.add(poolingBlock);
        setViewportView(jpanel);
        //add(jpanel);
        setVisible(true);
    }

    public Block[] getBlockComponents() {
        return new Block[]{convolutionLayerBlock,denseBlock,lstmBlock,poolingBlock};
    }
}
