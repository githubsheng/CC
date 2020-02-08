package leetcode;

import data.structure.TreeNode;
import references.input.InputUtil;

//23:44 - 24:00 ~ 16min
public class Problem572 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        String ss = preOrder(s);
        String st = preOrder(t);
        return ss.contains(st);
    }

    private String preOrder(TreeNode node) {
        if(node == null) return "x";
        String left = preOrder(node.left);
        int val = node.val;
        String right = preOrder(node.right);
        return ":" + val + ":" + left + ":" + right + ":";
    }

    public static void main(String[] args) {
        TreeNode s = InputUtil.stringToTreeNode("[3,4,5,1,2,null,null,0]");
        TreeNode t = InputUtil.stringToTreeNode("[4, 1, 2]");

        boolean ret = new Problem572().isSubtree(s, t);

        String out = InputUtil.booleanToString(ret);

        System.out.print(out);
    }

}
