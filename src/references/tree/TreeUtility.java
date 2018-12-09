package references.tree;

import data.structure.TreeNode;

/**
 * Created by wangsheng on 5/12/18.
 */
public class TreeUtility {

    public void dfs(TreeNode root) {
        if(root == null) {
            System.out.println("root is null");
            return;
        }
        _dfs(root);
    }

    private void _dfs(TreeNode node) {
        if(node == null) return;
        if(node.left == null && node.right == null) {
            //hitting a leave. todo: consider
        }
        _dfs(node.left);
        _dfs(node.right);
    }

}
