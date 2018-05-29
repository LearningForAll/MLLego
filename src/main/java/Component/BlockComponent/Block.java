package Component.BlockComponent;

import Component.BlockActionListener.DeleteActionListener;
import Component.BlockActionListener.ReductionActionListener;
import Component.BlockBatchModel.BlockTemplateComponent.BlockTemplate;
import Component.BlockException.BlockException;
import Component.BlockObserver.BlockObserver;
import Component.BlockObserver.BlockPublisher;
import Util.FileUtil;
import Util.UidGenerator;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
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
    private String uid;

    private BlockObserver blockObserver;
    protected List<Block> previousBlocks;
    protected List<Block> nextBlocks;
    private LineBorder basicBorder = new LineBorder(Color.black);
    private MatteBorder topBorder = new MatteBorder(5, 0, 0, 0, Color.cyan);
    private MatteBorder bottomBorder = new MatteBorder(0, 0, 5, 0, Color.cyan);

    protected abstract String getBlockAttrStr();
    // 인자로 들어온 블록이 현재 블록의 다음 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract public boolean isNextBlockConnectable(Block block);

    // 인자로 들어온 블록이 현재 블록의 이전 블록으로 연결 될 수 있는지 확인하는 메소드
    abstract public boolean isPreviousBlockConnectable(Block block);

    // 다음 블록과 연결되어 있는지 여부 연결되어있으면 true 리턴
    abstract public boolean isNextBlockConnected();

    // 이전 블록과 연결되어 있는지 여부
    abstract public boolean isPreviousBlockConnected();

    protected JLabel nameLabel;
    //public String blockName;
    public JPanel flowPanel;
    public JButton reductButton;
    public JButton revertExtendButton;
    private JPopupMenu popupMenu;
    private JMenuItem delete;
    public int diff;
    public int width;
    protected boolean extended = false;
    public int seemToExtend=1;//previousBlock이 그 전 블록에 의해 justExtended된 상태일 때
    public Block() {
        nextBlocks = new ArrayList<>();
        previousBlocks = new ArrayList<>();
        this.setBorder(basicBorder);
        addMouseListener(this);
        addMouseMotionListener(this);
        uid = UidGenerator.generateUid();

        this.setBorder(basicBorder);
        reductButton = new JButton(new ImageIcon(FileUtil.getResourcePath("icon/minus.png")));
        reductButton.addActionListener(new ReductionActionListener(this));
        reductButton.setEnabled(false);
        flowPanel = new JPanel(new FlowLayout());
        reductButton.setPreferredSize(new Dimension(16, 16));
        flowPanel.add(reductButton);
        flowPanel.setBackground(new Color(150, 0, 205));
        popupMenu=new JPopupMenu();
        delete=new JMenuItem("Delete");
        popupMenu.add(delete);

        // 만약 템플릿에서 불러왔을때...
        setInitialBlockSize();
        setVisible(true);
    }
    public Block(BlockTemplate blockTemplate){
        //TODO 완성해야함 좌표랑 다음블록 이전블록
        extended = blockTemplate.isExtended();
    }

    public void checkExtendBlock(Block block){
        if(block instanceof ExtendableBlock){
            ((ExtendableBlock)block).getExtendButton().setEnabled(true);
        }
    }

    //TODO boolean을 return 하거나 Exception 으로 Handle할수 있게
    // 인자로 넘어온 블록을 다음 블록으로 등록하는 함수
    public void registerNextBlock(Block block) throws BlockException {

        if (isNextBlockConnectable(block)) {
            //연결되면 ExtendableBlock일 경우, extendButton과 revertExtendButton을 비활성화 시킨다.
            if (block.isPreviousBlockConnectable(this)) {
                if(block instanceof ExtendableBlock){
                    ((ExtendableBlock) block).extendButton.setEnabled(false);
                    ((ExtendableBlock) block).revertExtendButton.setEnabled(false);
                }
                if(this instanceof ExtendableBlock){
                    ((ExtendableBlock) this).extendButton.setEnabled(false);
                    ((ExtendableBlock) this).revertExtendButton.setEnabled(false);
                }

                // 다음 블록으로 들어오는 블록의 location을 설정.
                if(block instanceof ExtendableBlock){
                    // 블록이 확장되어있는지 검사
                    // 다음 블록이 확장 되었을 때의 케이스
                    if((((ExtendableBlock) block).isBlockExtended() && !((ExtendableBlock) block).isFull())){
                        float plusWidth = block.getWidth() * ((float)(((ExtendableBlock) block).getConnectedSize()) / ((ExtendableBlock)(block)).getExtendSize());

                        if(this instanceof ExtendableBlock){
                            ((ExtendableBlock) block).addConnectedSize(((ExtendableBlock)this).getExtendSize());
                        }else{
                            ((ExtendableBlock) block).addConnectedSize(1);
                        }
                        this.nextBlocks.add(block);
                        int cumulative_y = 0;
                        for(Block block1 : getAllPreviousBlocks()){
                            cumulative_y = cumulative_y + block1.getHeight();
                            block1.setLocation(block.getX() + (int)plusWidth, block.getY() - cumulative_y);
                        }

                    }else if(this instanceof ExtendableBlock && (this.isBlockJustExtended() || ((ExtendableBlock)this).isBlockExtended())){
                        // 다음 블록이 Extendable 블록이지만 Extend 되어있진 않고 되려 현재 자신의 블록이 Extend되어있을때

                        block.setLocation(this.getX(), this.getY() + this.getHeight());
                        this.nextBlocks.add(block);
                        block.extendBlockJustSize(this);

                    }else if(this.isBlockJustExtended() && ((ExtendableBlock) block).isBlockExtended()){}
                    else {
                        int cumulative_y = 0;
                        this.nextBlocks.add(block);
                        for(Block block1 : getAllPreviousBlocks()){
                            cumulative_y = cumulative_y + block1.getHeight();
                            block1.setLocation(block.getX(), block.getY() - cumulative_y);
                        }
                    }
                }else if(block instanceof ClassifierBlock){

                    // 여기서는 체크만한다.
                    if (((ClassifierBlock) block).checkIfXConnectable(this)){
                        this.nextBlocks.add(block);
                        int moving_x = this.getX() - block.getX();
                        int moving_y = block.getY() - (this.getY() + this.getHeight());

                        for(Block block1 : getAllPreviousBlocks()){
                            block1.setLocation(block1.getX() - moving_x, block1.getY() + moving_y);
                        }
                        ((ClassifierBlock) block).extendXSize(this);

                    }else if(((ClassifierBlock) block).checkIfYConnectable(this)){

                        this.nextBlocks.add(block);
                        int moving_y = block.getY() - (this.getY() + this.getHeight());
                        for(Block block1 : getAllPreviousBlocks()){
                            block1.setLocation(block.getX() + block.getWidth() - block1.getWidth(), block1.getY() + moving_y);
                        }
                    }
                }else{
                    block.setLocation(this.getX(), this.getY() + this.getHeight());
                    this.nextBlocks.add(block);
                    if (this instanceof ClassifierBlock || this instanceof TrainingBlock){
                        block.extendBlockJustSize(this);
                    }

                }

            } else {
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
            }
        } else {
            throw new BlockException(block.getClass().getSimpleName() + " is not connectable Next block for" + this.getClass().getSimpleName());
        }
    }
    public void registerInitialNextBlock(Block block) throws BlockException{
        if(isNextBlockConnectable(block)){
            if (block.isPreviousBlockConnectable(this)){
                this.nextBlocks.add(block);
            }else{
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
            }
        }else{
            throw new BlockException(block.getClass().getSimpleName() + " is not connectable Next block for" + this.getClass().getSimpleName());

        }
    }

    // 인자로 넘어온 블록을 이전 블록으로 등록하는 함수
    public void registerPreviousBlock(Block block) throws BlockException {
        if (isPreviousBlockConnectable(block)) {
            if (block.isNextBlockConnectable(this)) {
                //연결되면 ExtendableBlock일 경우, extendButton과 revertExtendButton을 비활성화 시킨다.
                if(block instanceof ExtendableBlock){
                    ((ExtendableBlock) block).extendButton.setEnabled(false);
                    ((ExtendableBlock) block).revertExtendButton.setEnabled(false);
                }
                if(this instanceof ExtendableBlock){
                    ((ExtendableBlock) this).extendButton.setEnabled(false);
                    ((ExtendableBlock) this).revertExtendButton.setEnabled(false);
                }
                // 분류기 블록은 따로 취급해준다.
                if (this instanceof ClassifierBlock){
                    if (((ClassifierBlock)this).checkIfXConnectable(block)){
                        ((ClassifierBlock)this).setxPartBlock(block);
                        this.previousBlocks.add(block);
                    }else if (((ClassifierBlock)this).checkIfYConnectable(block)){
                        ((ClassifierBlock)this).setyPartBlock(block);
                        this.previousBlocks.add(block);
                    }

                }else{
                    this.previousBlocks.add(block);
                }
            } else {
                throw new BlockException(block.getClass().getSimpleName() + "is not connectable Next block for" + this.getClass().getSimpleName());
            }
        } else {
            throw new BlockException(block.getClass().getSimpleName() + "is not connectable Previous block for" + this.getClass().getSimpleName());
        }
    }

    /**
     * 아래쪽 보더를 셋해준다
     */
    public void blinkBottom() {

        try {
            this.setBorder(null);
            this.setBorder(bottomBorder);
            this.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 위쪽 보더를 셋해준다.
     */
    public void blinkTop() {
        try {
            this.setBorder(null);
            this.setBorder(topBorder);
            this.updateUI();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    //TODO 여기도 생성자 기본 테두리가 나오면 null 부분에 기본 테두리가 들어감
    public void revertBlock() {
        this.setBorder(null);
        this.setBorder(basicBorder);
        this.updateUI();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (contains(new Point(e.getX(), e.getY()))) {
            offX = e.getX();
            offY = e.getY();
            isDragged = true;

        }
        if (isFirstBlock()) {

            List<Block> allConnectedBlock = this.getAllConnectedBlock();

            for(int i = 0; i < allConnectedBlock.size(); i++){
                System.out.println("눌릴때");
                System.out.println("e.getx" + e.getX() + "getlocationX().x" + getLocation().x + "offset x" + offX);
                System.out.println(i + "번쨰 블록" + allConnectedBlock.get(i).getLocation().getX() + "//" + allConnectedBlock.get(i).getLocation().getY());
            }

        }
    }

    // Pressed 에서 Dragged로 블링크 블록을 옮김 실시간 좌표와 액션을 위해
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = 0;
        int y = 0;
        if (isDragged) {
            x = e.getX() + getLocation().x - offX;
            y = e.getY() + getLocation().y - offY;

            // 해당하는 블록이 첫번째 블록일때 뒤에 블록은 다 나를 따라와야한다.
            if (isFirstBlock()) {

                this.setLocation(x, y);
                List<Block> allConnectedBlock = this.getAllConnectedBlock();

                for(int i = 0; i < allConnectedBlock.size(); i++){
                    allConnectedBlock.get(i).setLocation(e.getX() + (int)allConnectedBlock.get(i).getLocation().getX() - offX, e.getY() + (int)allConnectedBlock.get(i).getLocation().getY() - offY);
                }
            }
            blockObserver.blinkBlock(this);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragged = false;
        blockObserver.revertOrConnectBlock(this);
    }

    //마우스 오른쪽 버튼을 누르면 delete가 팝업창으로 뜨면서 deleteActionListener 이벤트 발생
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3){//오른쪽버튼 클릭시
            popupMenu.show(Block.this, e.getX(), e.getY());
            delete.addActionListener(new DeleteActionListener(this));
        }
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
    public void setObserver(BlockObserver observer) {
        this.blockObserver = observer;
    }


    /**
     * 블록 테두리가 기본 테두리면 return false;
     * 이벤트에 의해 바뀐 테두리면 return true;l
     */
    public boolean checkBorder() {

        //TODO 블록 테두리 코드 받으면 null 부분대신 default 코드가 들어감
        if (this.getBorder() == basicBorder) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * TopBorder가 event에 의해 Trigger되었는지 여부
     */
    public boolean checkTopBorder() {
        // 자기 자신의 border가 topborder임을 체크
        if (this.getBorder() == topBorder) {
            return true;
        } else {
            return false;

        }
    }

    public boolean checkBottomBorder() {
        // 자기 자신의 border가 bottomBorder임을 체크
        if (this.getBorder() == bottomBorder) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFirstBlock() {
        return (this.previousBlocks.size() == 0);
    }


    public void disconnectBlock() {
        if (this instanceof ExtendableBlock){
            // Extendable 블록일때 extend사이즈를 줄여준다...
            if (((ExtendableBlock)this).isBlockExtended()){
                for(int j = 0; j < this.previousBlocks.size(); j++){
                    if (this.previousBlocks.get(j) instanceof ExtendableBlock){
                        ((ExtendableBlock)this).subConnectedSize(((ExtendableBlock) this.previousBlocks.get(j)).getExtendSize());
                    }else{
                        ((ExtendableBlock)this).subConnectedSize();
                    }
                }
            }
        }else if(this instanceof ClassifierBlock){
            ((ClassifierBlock)this).deleteXYBlock();
            ((ClassifierBlock)this).revertXSize();
        }
        for (int i = 0; i < this.previousBlocks.size(); i++){
            this.previousBlocks.get(i).disconnectNextBlock();
        }
        // 크기만 커져있을 블록의경우 크기를 줄여줌
        if(this.isBlockJustExtended()){
            this.revertExtendBlockJustSize();
        }

        if(this instanceof ExtendableBlock) {
            if (((ExtendableBlock) this).isPreviousBlockConnected()) {
                if (((ExtendableBlock) this).previousBlocks.get(this.previousBlocks.size() - 1) instanceof ExtendableBlock) {
                    ((ExtendableBlock) ((ExtendableBlock) this).previousBlocks.get(this.previousBlocks.size() - 1)).activateButton();
                }
            }
        }
        this.disconnectPreviousBlock();

        //연결이 끊어지면 extendable블록의 버튼들을 다시 활성화시켜줌
        if(this instanceof ExtendableBlock) {
            ((ExtendableBlock) this).activateButton();
            if (((ExtendableBlock) this).isNextBlockConnected()) {
                if (((ExtendableBlock) this).nextBlocks.get(0) instanceof ExtendableBlock) {
                    ((ExtendableBlock) ((ExtendableBlock) this).nextBlocks.get(0)).activateButton();
                }
            }
        }

    }

    public void disconnectForBlock() {
        if (this.isPreviousBlockConnected()) {
            for (int i = 0; i < previousBlocks.size(); i++) {
                this.previousBlocks.get(i).disconnectNextBlock();
            }
            this.disconnectPreviousBlock();
            if (this.isNextBlockConnected()) {
                this.nextBlocks.get(0).disconnectPreviousBlock();
            }
            this.disconnectNextBlock();
        }
    }

    //Reduction상태일때의 연결 변경되었으니 previous block들의 location이 바뀌는 걸로 수정
    public void setFollowBlockPosition(boolean isReducted) {
        Block block = this;
        if (isReducted) {
            // 줄어든 상황에서 늘어나야함
                List<Block> allBlock =block.getAllPreviousBlocks();
                allBlock.remove(0);
                for(Block blocks:allBlock){
                    blocks.setLocation(blocks.getX(), blocks.getY()-(this.getHeight()-this.flowPanel.getHeight())-this.diff);
                    System.out.println(blocks.getY()-(getHeight()-flowPanel.getHeight())-block.diff);
                }
                //연결되어있으면
                if (block.isPreviousBlockConnected()) {
                    block = block.previousBlocks.get(0);
                } else {
                }
          //  }

        } else {
            // 아직 줄어들지 않은 상황에서
                List<Block> allBlock =block.getAllPreviousBlocks();
                allBlock.remove(0);

                for(Block blocks:allBlock){
                    blocks.setLocation(blocks.getX(), blocks.getY()+(this.getHeight()-this.flowPanel.getHeight()));
                    System.out.println(blocks.getY()+(getHeight()-flowPanel.getHeight()));
                }
                if (block.isPreviousBlockConnected()) {
                    block = block.previousBlocks.get(0);
                } else {
                }
        }
    }

    public Block getLastConnectedBlock() {
        Block block = this;
        while (block.isNextBlockConnected()) {
            //연결되어있으면
            if (block.isNextBlockConnected()) {
                block = block.nextBlocks.get(0);
            } else {
                return block;
            }
        }
        return block;
    }

    public void disconnectNextBlock() {
        this.nextBlocks.clear();
    }

    public void disconnectPreviousBlock() {
        this.previousBlocks.clear();
    }
    public String getUid(){
        return uid;
    }
    public void setUid(String uid){
        this.uid = uid;
    }

    public List<Block> getPreviousBlocks(){
        return previousBlocks;
    }

    public List<Block> getNextBlocks(){
        return nextBlocks;
    }

    public List<Block> getAllConnectedBlock(){

        List<Block> allConnectedBlock = new ArrayList<>();

        Block block = this;
        while (block.isNextBlockConnected()) {

            if(block.nextBlocks.get(0) instanceof ExtendableBlock){
                if (((ExtendableBlock) block.nextBlocks.get(0)).isBlockExtended()){
                    int blockIndex = ((ExtendableBlock) block.nextBlocks.get(0)).getBlockIndex(block);
                    for (int i = 0; i < ((ExtendableBlock) block.nextBlocks.get(0)).previousBlocks.size(); i++){
                        if (i != blockIndex){
                            allConnectedBlock.addAll(((ExtendableBlock) block.nextBlocks.get(0)).previousBlocks.get(i).getAllPreviousBlocks());
                        }
                    }
                }
            }
            if(block.nextBlocks.get(0) instanceof ClassifierBlock){
                if(((ClassifierBlock)block.nextBlocks.get(0)).getyPartBlock() != null && !((ClassifierBlock) block.nextBlocks.get(0)).getyPartBlock().equals(block)){
                    allConnectedBlock.addAll(block.nextBlocks.get(0).previousBlocks.get(1).getAllPreviousBlocks());
                }else if(((ClassifierBlock)block.nextBlocks.get(0)).getxPartBlock() != null && !((ClassifierBlock) block.nextBlocks.get(0)).getxPartBlock().equals(block)){
                    allConnectedBlock.addAll(block.nextBlocks.get(0).previousBlocks.get(0).getAllPreviousBlocks());
                }
            }
            //연결되어있으면
            if (block.isNextBlockConnected()) {
                allConnectedBlock.add(block.nextBlocks.get(0));
                block = block.nextBlocks.get(0);
            } else {
                allConnectedBlock.add(block);
                break;
            }

        }
        return allConnectedBlock;


    }
    private List<Block> getAllPreviousBlocks(){
        // 자기 자신을 포함한 모든 이전블록을 리턴
        // Extendable 블록일때도 고려해 줘야함..
        List<Block> allPreviousBlock = new ArrayList<>();
        Block block = this;
        allPreviousBlock.add(block);
        while(block.isPreviousBlockConnected()){

            if(block.isPreviousBlockConnected()){
                if (block instanceof ExtendableBlock){
                    for (int i = 0; i < ((ExtendableBlock) block).previousBlocks.size(); i++){
                        allPreviousBlock.addAll(((ExtendableBlock) block).previousBlocks.get(i).getAllPreviousBlocks());
                    }
                    break;
                }
                if(block instanceof ClassifierBlock){
                    for(int i=0; i<((ClassifierBlock)block).previousBlocks.size();i++){
                        allPreviousBlock.addAll(((ClassifierBlock)block).previousBlocks.get(i).getAllPreviousBlocks());
                    }
                }
                else{
                    allPreviousBlock.add(block.previousBlocks.get(0));
                }
            }

            block = block.previousBlocks.get(0);
        }
        System.out.println(allPreviousBlock.size());

        return allPreviousBlock;
    }

    private void extendBlockJustSize(Block block){
        if(!this.extended){
            int beforeHeight = this.getHeight();
            int beforeWidth = this.getWidth();
            if(block instanceof ExtendableBlock){
                this.setSize(((ExtendableBlock)block).getExtendSize() * beforeWidth, beforeHeight);
            }else{
                this.setSize(block.getWidth(), beforeHeight);
            }
            this.extended = true;
        }
    }
    private void revertExtendBlockJustSize(){
        int beforeHeight = this.getHeight();
        if (this.extended){
            if(this.getWidth() > this.width){
                this.setSize(this.width, beforeHeight);
            }
        }
        this.extended = false;
    }

    private void setInitialBlockSize(){
        if(this.extended){
            if(this.getWidth() > this.width){
                this.setSize(this.previousBlocks.get(0).getWidth(), this.getHeight());
            }
        }
    }

    public boolean isBlockJustExtended(){
        return extended;
    }

    public int getSeemToExtend(){
        return seemToExtend;
    }

    public int addSeemToExtend(){
        seemToExtend++;
        return seemToExtend;
    }

    public void subSeemToExtend(){
        seemToExtend--;
    }

    protected void setExtended(boolean extended){
        this.extended = extended;
    }


}
