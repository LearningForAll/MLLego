package ML.Core;

import Component.BlockComponent.ModelBlock;

public interface ProcessListener {
    /**
     * 트레이닝 할때 트레이닝 모듈이 결과 과정 메세지를 보냈을때 처리할 함수.
     * @param message
     * 트레이닝 모듈로부터 도착한 메세지 내용.
     */
    void onResponseMessage(String message);

    /**
     *
     * @param modelBlock
     */
    void onFinish(ModelBlock modelBlock);
}
