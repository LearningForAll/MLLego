package ML.Core;

import Component.BlockComponent.ModelBlock;

public interface ProcessListener {
    enum Code {
        CODE_INFO,
        CODE_ERR
    }

    /**
     * 트레이닝 할때 트레이닝 모듈이 결과 과정 메세지를 보냈을때 처리할 함수.
     *
     * @param message
     * 트레이닝 모듈로부터 도착한 메세지 내용.
     * @param code
     * 트레이닝 모듈로부터 도착한 코드내용.
     *
     * @see Code
     */
    void onResponseMessage(String message,Code code);

    /**
     * 블록 훈련이 끝나면 도착하는 이벤트.
     *
     * @param modelBlock
     * 트레이닝 모듈로부터 완성되어 도착한 ModelBlockTemplate 오브젝트
     */
    void onFinish(ModelBlock modelBlock);
}
