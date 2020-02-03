package references.input;

import data.structure.TreeNode;

import java.util.*;

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

    public static int[][] parseToNestedArrays(String input) {
        List<List<Integer>> t = parseToNestedList(input);
        int l1 = t.size();
        int l2 = t.get(0).size();
        int[][] ret = new int[l1][l2];
        for (int i = 0; i < l1; i++) {
            ret[i] = t.get(i).stream().mapToInt(e -> e).toArray();
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

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }
}
