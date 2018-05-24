package Component.BlockComponent;

import Component.BlockActionListener.ExtendActionListener;
import Component.BlockActionListener.RevertExtendActionListener;
import Util.FileUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public abstract class ExtendableBlock extends Block {

    JButton extendButton;

    private int extendSize = 1;
    private int connectedSize = 0;

    public ExtendableBlock(){
        super();
        extendButton = new JButton(new ImageIcon(FileUtil.getResourcePath("icon/plus.png")));
        extendButton.addActionListener(new ExtendActionListener(this));
        extendButton.setPreferredSize(new Dimension(16, 16));
        extendButton.setEnabled(false);

        revertExtendButton=new JButton(new ImageIcon(FileUtil.getResourcePath("icon/undo.png")));
        revertExtendButton.addActionListener(new RevertExtendActionListener(this));
        revertExtendButton.setPreferredSize(new Dimension(16, 16));
        revertExtendButton.setEnabled(false);

        //Template에서 커넥션 정보 업데이트하고 사이즈 조정할때
        setInitialExtendableBlockSize();
    }
    public boolean isBlockExtended(){
        return extendSize != 1;
    }

    @Override
    public boolean isNextBlockConnected() {
        // 연결되었지만 블록공간이 남을경우 nextBlock이 올수있음
        return (nextBlocks.size() != 0);
    }

    @Override
    public boolean isPreviousBlockConnected() {
        return (extendSize - previousBlocks.size() == 0);
    }

    public boolean isFull(){
        return (extendSize - connectedSize == 0);
    }
    public void addConnectedSize(int size){
        connectedSize = connectedSize + size;
    }
    public void subConnectedSize(int size){
        connectedSize = connectedSize - size;
    }
    public void subConnectedSize(){
        connectedSize--;
    }
    public void setConnectedSize(int connectedSize){
        this.connectedSize = connectedSize;
    }

    public int getExtendSize(){
        return extendSize;
    }
    public void setExtendSize(int extendSize){
        this.extendSize = extendSize;
    }
    public int addExtendSize(){
        extendSize++;
        return extendSize;
    }
    public void subExtendSize(){
        extendSize--;
    }
    public void subExtendSize(int size){
        if(extendSize - size >= 0){
            extendSize = extendSize - size;
        }else{
            System.out.println("Cannnot subtract");
        }
    }
    public int getBlockIndex(Block block){
        return this.previousBlocks.indexOf(block);
    }
    public JButton getExtendButton(){
        return this.extendButton;
    }
    public int getConnectedSize(){
        return connectedSize;
    }

    private List<Block> getAllPreviousBlocksForExtendableBlock(){
        List<Block> allPreviousBlock = new ArrayList<>();
        Block block = this;

        for (int i = 0; i < this.previousBlocks.size(); i++){
            // Extendable 블록은 previousBlock이 여러개 있기 때문에..
            allPreviousBlock.addAll(((ExtendableBlock)block.previousBlocks.get(i)).getAllPreviousBlocks());
        }

        return allPreviousBlock;
    }
    private List<Block> getAllPreviousBlocks(){
        // 자기 자신을 제외한 블록의 이전블록들을 구함.
        List<Block> allPreviousBlock = new ArrayList<>();
        Block block = this;
        //allPreviousBlock.add(block);
        while(block.isPreviousBlockConnected()){

            if(block.isPreviousBlockConnected()){
                allPreviousBlock.add(block.previousBlocks.get(0));
            }

            block = block.previousBlocks.get(0);
        }

        return allPreviousBlock;
    }

    public void setInitialExtendableBlockSize(){
        if(isBlockExtended()){
            //TODO 사이즈를 늘려야함
            this.setSize(this.getWidth() * extendSize, this.getHeight());
        }
    }
}
