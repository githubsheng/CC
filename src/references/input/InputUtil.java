package references.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sheng.wang on 2018/12/06.
 */
@SuppressWarnings("Duplicates")
public class InputUtil {

    //this assumes format of [[1, 2, 3] , [3, 4, 5], [ 7, 8, 9 ]]
    public static List<List<Integer>> parseToNestedList(String input) {

        input = input.trim();
        if(input.charAt(0) != '[' && input.charAt(input.length() - 1) != ']') throw new IllegalArgumentException("wrong format");
        if(input.length() == 2) return Collections.emptyList();
        input = input.substring(1, input.length() - 1);

        List<List<Integer>> ret = new ArrayList<>();

        int listStart = -1;
        int listEnd = -1;
        for(int i = 0, l = input.length(); i < l; i++) {
            char c = input.charAt(i);
            if(c == '[') listStart = i;
            if(c == ']') {
                listEnd = i;

                if(listEnd - listStart == 1) {
                    ret.add(Collections.emptyList());
                } else {
                    String subStr = input.substring(listStart + 1, listEnd);
                    if(subStr.trim().isEmpty()) {
                        ret.add(Collections.emptyList());
                    } else {
                        String[] elms = subStr.split(",");
                        List<Integer> temp = new ArrayList<>();
                        for(String elm : elms) {
                            temp.add(Integer.parseInt(elm.trim()));
                        }
                        ret.add(temp);
                    }
                }
            }

        }

        return ret;
    }

    public static List<Integer> parseToList(String input){
        int listStart = -1;
        int listEnd = -1;
        for(int i = 0, l = input.length(); i < l; i++) {
            char c = input.charAt(i);
            if(c == '[') listStart = i;
            if(c == ']') {
                listEnd = i;

                if(listEnd - listStart == 1) {
                    return Collections.emptyList();
                } else {
                    String subStr = input.substring(listStart + 1, listEnd);
                    if(subStr.trim().isEmpty()) {
                        return Collections.emptyList();
                    } else {
                        String[] elms = subStr.split(",");
                        List<Integer> ret = new ArrayList<>();
                        for(String elm : elms) {
                            ret.add(Integer.parseInt(elm.trim()));
                        }
                        return ret;
                    }
                }
            }

        }
        throw new RuntimeException("something wrong");
    }


    public static void main(String[] args) {
        List<List<Integer>> ret0 = parseToNestedList(" [ ]");
        List<List<Integer>> ret1 = parseToNestedList(" [ [1,2, 3], [4, 6, 7], [1, 2,    3], [ ], []]");
        List<Integer> ret2 = parseToList(" [3, 4, 5] ");
        List<Integer> ret3 = parseToList("[ ]");
        System.out.println(1);
    }




}
