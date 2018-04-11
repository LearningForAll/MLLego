package Util;

public class StringUtil {
    public static String stringContainsItemFromList(String inputStr, String[] items){
        for(int i =0; i < items.length; i++)
        {
            if(inputStr.contains(items[i]))
            {
                return items[i];
            }
        }
        return null;
    }
}
