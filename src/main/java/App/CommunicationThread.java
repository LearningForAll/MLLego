package App;

import Presentation.Controller.ResultController;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicationThread extends Thread {
    public int Port = 9999;
    public boolean runningSignal = true;
    @Override
    public void run() {
        super.run();
        while (runningSignal) {
            try (ServerSocket server = new ServerSocket()) {
                InetSocketAddress ipep = new InetSocketAddress(Port);
                server.bind(ipep);
                Socket client = server.accept();
                System.out.println("connected");
                while (runningSignal) {
                    InputStream reciever = client.getInputStream();
                    byte[] buff = new byte[1024];
                    int readCount = reciever.read(buff, 0, buff.length);
                    if (readCount == -1) break;
                    String readStr = new String(buff);
                    for (String oneData : readStr.split("_END")){
                        ResultController.getInstance().addResultLine(oneData);
                    }

                }
                System.out.println("dis connected");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
