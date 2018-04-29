package Util;

public class UidGenerator {
    private static String BASE_UID_NAME = "block";
    private static int autoIncrease = 0;
    public static String generateUid(){
        autoIncrease++;
        return BASE_UID_NAME+autoIncrease;
    }
}
