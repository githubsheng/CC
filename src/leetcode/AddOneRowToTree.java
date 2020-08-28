package leetcode;

import data.structure.TreeNode;

//some comments here
public class AddOneRowToTree {


    public TreeNode addOneRow(TreeNode root, int v, int d) {

        if(d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        } else {
            dfs(root, v, 1, d - 1);
        }

        return root;
    }

    private void dfs(TreeNode node, int v, int currentDepth, int targetSwapDepth){

        if(node == null) return;

        if(currentDepth == targetSwapDepth) {

            TreeNode prevLeft = node.left;
            node.left = new TreeNode(v);
            node.left.left = prevLeft;

            TreeNode prevRight = node.right;
            node.right = new TreeNode(v);
            node.right.right = prevRight;

        } else {
            dfs(node.left, v, currentDepth + 1, targetSwapDepth);
            dfs(node.right, v, currentDepth + 1, targetSwapDepth);
        }

    }

}
