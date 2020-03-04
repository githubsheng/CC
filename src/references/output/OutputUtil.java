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
            System.out.print(" " + array[i]);
        }
        System.out.println("");
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

}



