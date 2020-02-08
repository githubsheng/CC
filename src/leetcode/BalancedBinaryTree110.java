package leetcode;

import data.structure.TreeNode;

/**
 * Created by wangsheng on 9/12/18.
 */
public class BalancedBinaryTree110 {


    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            System.out.println("root is null");
            return true;
        }
        int ret = _dfs(root);
        return ret > 0;
    }

    private int _dfs(TreeNode node) {
        if(node == null) return 0;
        if(node.left == null && node.right == null) {
            return 1;
        }
        int leftHeight = _dfs(node.left);

        if(leftHeight < 0) return -1;

        int rightHeight = _dfs(node.right);
        if(rightHeight < 0) return -1;

        if(Math.abs(leftHeight - rightHeight) <= 1) {
            return Math.max(leftHeight, rightHeight) + 1;
        } else {
            return -1;
        }

    }


}
