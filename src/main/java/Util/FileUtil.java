package Util;

import App.MyApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class FileUtil {
    public static File resourceLoad(String resourcePath){
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        try {
            URI resourceURI =classLoader.getResource(resourcePath).toURI();
            return Paths.get(resourceURI).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getResourcePath(String resourcePath){
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        try {
            URI resourceURI =classLoader.getResource(resourcePath).toURI();
            return Paths.get(resourceURI).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void fileCopy(String inFileName, String outFileName) {
        fileCopy(new File(inFileName),outFileName);
    }
    public static void fileCopy(File inFile, String outFileName) {
        try {
            FileInputStream fis = new FileInputStream(inFile);
            FileOutputStream fos = new FileOutputStream(outFileName);

            int data = 0;
            while((data=fis.read())!=-1) {
                fos.write(data);
            }
            fis.close();
            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getCurrentProjectDirPath() {
        String currentDir = System.getProperty("user.dir");
        String folderDir = currentDir+"\\bin\\"+ MyApp.projectTitle;
        int index = 0;
        while (new File(folderDir).isDirectory()){
            index++;
            folderDir =  currentDir+"\\bin\\"+ MyApp.projectTitle+index;
        }
        MyApp.projectTitle+=""+index;
        File dir = new File(folderDir);
        if(!dir.mkdirs()) throw new RuntimeException("안된다고 안만들어진다고!");
        return folderDir;
    }
}
