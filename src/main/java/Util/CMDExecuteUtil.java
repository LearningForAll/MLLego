package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CMDExecuteUtil {

    public static void cmdExecute(String [] cmd) throws Exception{

        ProcessBuilder pBuilder = new ProcessBuilder(cmd); // 실행할 cmd값 준비
        Process process       = null;
        InputStream is        = null; // 입력용 바이트스트림
        InputStreamReader isr = null; // 문자 스트림을 읽기 위한 클래스
        BufferedReader br     = null; // 다른 Reader들을 버퍼링 하기 위한 클래스

        try {

            process = pBuilder.start(); // cmd 커맨드 창을 실행 후  ProcessBuilder 명령어를 실행
            is      = process.getInputStream();
            isr     = new InputStreamReader(is);
            br      = new BufferedReader(isr);
            br.close();
            isr.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            process.waitFor(); // 프로세스 종료를 기다린다..
        }

    }
}