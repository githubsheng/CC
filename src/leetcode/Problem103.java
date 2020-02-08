package leetcode;

import data.structure.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//18:08 ~ 18:23 - 15 min
public class Problem103 {

    private List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        preOrder(root, 0);
        for (int i = 0; i < ret.size(); i++) {
            if(i % 2 != 0) {
                //odd
                Collections.reverse(ret.get(i));
            }
        }
        return ret;
    }

    private void preOrder(TreeNode node, int level) {
        if (node == null) return;

        if(ret.size() <= level) ret.add(new ArrayList<>());
        ret.get(level).add(node.val);
        preOrder(node.left, level + 1);
        preOrder(node.right, level + 1);
    }

    public static void main(String[] args) {

    }

}
