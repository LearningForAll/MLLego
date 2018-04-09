package Component.BlockComponent;

import Component.BlockException.BlockException;
import Component.BlockObserver.BlockObserver;
import Component.BlockObserver.BlockPublisher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;


// 관찰될 수 있는 객체
public abstract class Block extends JPanel implements MouseListener, MouseMotionListener, BlockPublisher {
    private int offX, offY;
    private boolean isDragged = false;

    private BlockObserver blockObserver;
    protected List<Block> previousBlocks;
    protected List<Block> nextBlocks;
    private JComponent topComponent, bottomComponent;
    abstract String getBlockAttrStr();

    // 인자로 들어온 블록이 현재 블록의 다음 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract public boolean isNextBlockConnectable(Block block);
    // 인자로 들어온 블록이 현재 블록의 이전 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract public boolean isPreviousBlockConnectable(Block block);
    // 다음 블록과 연결되어 있는지 여부 연결되어있으면 true 리턴
    abstract public boolean isNextBlockConnected();
    // 이전 블록과 연결되어 있는지 여부
    abstract public boolean isPreviousBlockConnected();


    public Block(){
        nextBlocks = new ArrayList<>();
        previousBlocks = new ArrayList<>();
    }

    //TODO boolean을 return 하거나 Exception 으로 Handle할수 있게
    // 인자로 넘어온 블록을 다음 블록으로 등록하는 함수
    public void registerNextBlock(Block block) throws BlockException{
        if(isNextBlockConnectable(block)){
            if (block.isPreviousBlockConnectable(this)){
                this.nextBlocks.add(block);
                block.previousBlocks.add(this);
            }else{
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
            }
        }else{
            throw new BlockException(block.getClass().getSimpleName() + "is not connectable Next block for" + this.getClass().getSimpleName());
        }
    }
    // 인자로 넘어온 블록을 이전 블록으로 등록하는 함수
    public void registerPreviousBlock(Block block) throws BlockException {
        if (isPreviousBlockConnectable(block)) {
            if (block.isNextBlockConnectable(this)) {
                this.previousBlocks.add(block);
                block.nextBlocks.add(this);
            } else {
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Next block for" + this.getClass().getSimpleName());
            }
        } else {
            throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
        }
    }
    /**
     * 블록의 하단을 반짝거리기 /  취소 하는 함수
     * @return
     * true : 블록 반짝임
     * false : 반짝임 취소
     */
    public boolean blinkBottom(){
        bottomComponent.setOpaque(!isOpaque());
        return bottomComponent.isOpaque();
    }

    /**
     * 블록의 상단을 반짝거리기 /  취소 하는 함수
     * @return
     * true : 블록 반짝임
     * false : 반짝임 취소
     */
    public boolean blinkTop(){
        topComponent.setOpaque(!isOpaque());
        return topComponent.isOpaque();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (contains(new Point(e.getX(), e.getY()))) {
            offX = e.getX();
            offY = e.getY();
            isDragged = true;
            blockObserver.blinkBlock(this);

        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = 0;
        int y = 0;
        if (isDragged) {
            x = e.getX()+getLocation().x-offX;
            y = e.getY()+getLocation().y-offY;
            setLocation(x,y);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        isDragged = false;
        blockObserver.revertBlock(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void setObserver(BlockObserver observer){
        this.blockObserver = observer;
    }

}
