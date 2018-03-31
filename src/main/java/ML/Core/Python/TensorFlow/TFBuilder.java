package ML.Core.Python.TensorFlow;

import Component.BlockComponent.Block;
import Component.BlockComponent.ClassifierBlock;
import Component.BlockComponent.TrainingBlock;
import ML.Core.Exception.IncompleteBlockException;
import ML.Core.Exception.NoClassificationException;
import ML.Core.MLBuilder;
import ML.Core.ProcessListener;

public class TFBuilder implements MLBuilder {
    ProcessListener processListener = null;
    String recentGenerateTrainingCodeURL = null;
    @Override
    public void setProcessListener(ProcessListener processListener) {
        this.processListener = processListener;
    }

    /*
      TODO :: 블록에 맞게 소스코드작성해서 URL 경로에 파일저장.
      TODO :: 블록별 소스코드 작성 모듈 생성해야함
      TODO :: 비동기가 아닌 동기 작업. 따라서 리스너 호출필요 X
      */
    /**
     *
     * @param trainingBlock
     * 트레이닝 블록부터 거꾸로 올라가면서 모든 블록파악 및 코드생성
     * 양방향 트리 모양이 형성됨
     *
     * 거꾸로 올라가면서 n의 부모를 가질시
     * "왼쪽" 우선 순회
     * @return
     * 성공,실패 반환
     */
    @Override
    public boolean generateCodeFile(TrainingBlock trainingBlock) {
        ClassifierBlock classifierBlock = trainingBlock.getClassifierBlock();
        if (classifierBlock == null) throw new NoClassificationException("classification 블록이 없습니다.",trainingBlock);

        Block xPartBlock = classifierBlock.getxPartBlock();
        Block yPartBlock = classifierBlock.getyPartBlock();
        if (xPartBlock == null || yPartBlock == null) throw new IncompleteBlockException("x 또는 y 파트의 블록이 존재하지않습니다");


        return false;
    }

    /*
     * TODO :: 파이썬 실행 모듈과 소켓통신하며 트레이닝함.
     * TODO :: 소켓통신 연결에 성공한경우 true, 실패한경우 false
     * TODO :: 새 쓰래드 생성하여 소켓에서 메세지에 따라 Listener 응답함.
     */
    @Override
    public boolean training() {
        return false;
    }

}
