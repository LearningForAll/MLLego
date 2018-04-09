package Component.BlockComponent;

import javax.swing.*;
import java.awt.*;
import Const.FileType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Created by chaebyeonghun on 2018. 3. 27..
 */
public class PreprocessorBlock extends Block {

    //TODO 옵션 추가와 파일 타입에 따른 UI 구성이 필요
    JRadioButton rgbOptionRadioButton;
    JRadioButton bwOptionRadioButton;
    JRadioButton onehotOptionRadioButton;
    JRadioButton dimReductOptionRadioButton;
    JPanel imagePanel;
    JPanel textPanel;
    FileType fileType;

    public PreprocessorBlock(){
        JLabel nameLabel=new JLabel("Preprocessor Block");
        rgbOptionRadioButton=new JRadioButton("RGB");
        bwOptionRadioButton=new JRadioButton("Black/White");
        onehotOptionRadioButton=new JRadioButton("One-hot Encoding");
        dimReductOptionRadioButton=new JRadioButton("Dimension Reduction");

        imagePanel=new JPanel(new FlowLayout(FlowLayout.LEADING,3,2));
        rgbOptionRadioButton.setPreferredSize(new Dimension(50,20));
        bwOptionRadioButton.setPreferredSize(new Dimension(50,20));
        imagePanel.add(rgbOptionRadioButton);
        imagePanel.add(bwOptionRadioButton);

        textPanel=new JPanel(new FlowLayout(FlowLayout.LEADING,3,2));
        onehotOptionRadioButton.setPreferredSize(new Dimension(50,20));
        dimReductOptionRadioButton.setPreferredSize(new Dimension(50,20));
        textPanel.add(onehotOptionRadioButton);
        textPanel.add(dimReductOptionRadioButton);

        JPanel flowPanel3=new JPanel(new FlowLayout(FlowLayout.LEADING, 3, 2));
        JLabel explainLabel=new JLabel("Need to connect with input block");
        explainLabel.setPreferredSize(new Dimension(200,20));
        explainLabel.setHorizontalAlignment(explainLabel.CENTER);
        explainLabel.setFont(new Font("BOLD", Font.BOLD ,11));
        explainLabel.setForeground(Color.gray);
        flowPanel3.add(explainLabel);

        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(nameLabel.CENTER);
        TitledBorder line=new TitledBorder(new LineBorder(Color.black));
        GridLayout layout=new GridLayout(2,1);
        setLayout(layout);
        setBorder(line);
        add(nameLabel);
        add(flowPanel3);
        setBackground(new Color(243,115,50));
        setVisible(true);
    }
    @Override
    String getBlockAttrStr() {
        return null;
    }

    @Override
    boolean isNextBlockConnectable(Block block) {
        return (block instanceof LayerBlock);
    }

    @Override
    boolean isPreviousBlockConnectable(Block block) {
        if(block instanceof InputBlock){
            getFileType((InputBlock) block);
        }
        return (block instanceof InputBlock);
    }
    // Preprocessor랑 Input블록이 연결될때 무조건 호출해야하는 함수
    public void getFileType(InputBlock inputBlock){
        //이전 Input 블록에서 파일타입을 가져옴
        fileType = inputBlock.getFileType();
    }

    //TODO : preprocessor 분기에 따라 활성화 시켜줄 요소들 보여주기
    public void activateElement(){
        if(fileType==FileType.TYPE_TEXT){
            this.
        }
        if(fileType==FileType.TYPE_IMAGE){

        }
    }

}
