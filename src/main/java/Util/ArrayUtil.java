package Util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by LG on 2018-04-12.
 */

//배열 합치기 위한 클래스
public class ArrayUtil {
    public static <T> T[] merge1(T[]... arrays) {
        int length = Arrays.stream(arrays).mapToInt(a -> a.length).sum();
        T[] merged = (T[]) Array.newInstance(
                arrays[0].getClass().getComponentType(),
                length
        );
        int destPos = 0;
        for (T[] arr : arrays) {
            System.arraycopy(arr, 0, merged, destPos, arr.length);
            destPos += arr.length;
        }
        return merged;
    }
}
