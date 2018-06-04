package App;

import Presentation.Controller.BlockPlacementDefaultController;
import Presentation.Controller.ModelTestController;
import Presentation.Controller.MyModelDefaultController;
import Presentation.Controller.ResultController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicationThread extends Thread {
    public int Port = 9999;
    public boolean runningSignal = true;
    Socket recentSocket;
    @Override
    public void run() {
        super.run();
        while (runningSignal) {
            try (ServerSocket server = new ServerSocket()) {
                InetSocketAddress ipep = new InetSocketAddress(Port);
                server.bind(ipep);
                Socket client = server.accept();
                recentSocket = client;
                System.out.println("connected");
                while (runningSignal) {
                    InputStream reciever = client.getInputStream();
                    byte[] buff = new byte[1024];
                    int readCount = reciever.read(buff, 0, buff.length);
                    if (readCount == -1) break;
                    String readStr = new String(buff);
                    for (String oneData : readStr.split("_END")){
                        oneData = oneData.trim();
                        if (oneData.isEmpty() || oneData.equals(" ") || oneData.length()<5) continue;
                        if (oneData.contains("type_test")){
                            ModelTestController.getInstance().addResultLine(oneData.split("type_test")[1]);
                        }else{
                            ResultController.getInstance().addResultLine(oneData);
                        }
                    }
                }
                System.out.println("dis connected");
            } catch (Throwable e) {
                e.printStackTrace();
            }
            BlockPlacementDefaultController.getInstance().resetResultPanel();
            MyModelDefaultController.getInstance().restartMyModelList();
        }
    }

    public void SendStopSignal(){
        try {
            OutputStream os = recentSocket.getOutputStream();
            String stop = "stop";
            os.write(stop.getBytes("UTF-8"));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
