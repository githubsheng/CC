package references.output;

import java.util.List;

/**
 * Created by wangsheng on 9/12/18.
 */
public class OutputUtil {

    /**
     * print the list from start index to end index (inclusive)
     * @param list
     * @param start
     * @param end
     * @param <T>
     */
    public static <T> void printList(List<? extends T> list, int start, int end) {

        for(int i = start; i < end; i++) {
            System.out.println(i + " : " + list.get(i));
        }

    }

    public static <T> void printList(List<? extends T> list) {
        printList(list, 0, list.size());
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

}



