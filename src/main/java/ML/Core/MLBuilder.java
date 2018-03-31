package ML.Core;

import Component.BlockComponent.TrainingBlock;

public interface MLBuilder {
    /**
     * 프로세스 리스너 등록
     * @param processListener
     * 프로세스 리스너 등록
     */
    void setProcessListener(ProcessListener processListener);

    /**
     * 코드파일 생성함수.
     * @param trainingBlock
     * 트레이닝 블록부터 거꾸로 올라가면서 모든 블록파악 및 코드생성
     * 양방향 트리 모양이 형성됨
     *
     * @return
     * 코드파일이 정상적으로 생성되었는지 반환
     */
    boolean generateCodeFile(TrainingBlock trainingBlock);

    /**
     * 트레이닝 요청 함수. 가장 최근 생성된 codeFile을 트레이닝함
     * @see #generateCodeFile(TrainingBlock)
     *
     * @return
     * 트레이닝 요청 성공 실패를 반환.
     * false : 최근 생성된 소스코드 파일이 없다는 뜻
     */
    boolean training();
}
