package App;

import Presentation.View.MainFrame;


public class MyApp {
    public static String projectTitle = "untitled";
    public static String templateFolder = "template";
    public static ClassLoader classLoader;


    static CommunicationThread communicationThread;
    public static void main(String[] args){
        new MainFrame();
        communicationThread = new CommunicationThread();
        communicationThread.start();
    }

    public static void stop(){
        communicationThread.SendStopSignal();
    }
}
