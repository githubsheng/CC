package leetcode;

import data.structure.TreeNode;

//11:03 - 11:21 ~ 18min
public class Problem297 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String encoded = preOrder(root);
        System.out.println(encoded);
        return encoded;
    }

    private String preOrder(TreeNode node) {
        if(node == null) return "null";
        String leftPart = preOrder(node.left);
        String rightPart = preOrder(node.right);
        String self = String.valueOf(node.val);
        return self + ":" + leftPart + ":" + rightPart;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] parts = data.split(":");
        return deserializeHelper(parts, 0).node;
    }


    private TreeNodeAndIndex deserializeHelper(String[] parts, int start) {
        String rootPart = parts[start];
        //null
        if(rootPart.equals("null")) {
            return new TreeNodeAndIndex(null, start);
        }

        //not null, node
        TreeNode node = new TreeNode(Integer.parseInt(rootPart));


        //do left, start ++;
        TreeNodeAndIndex leftRet = deserializeHelper(parts, ++start);
        node.left = leftRet.node;

        start = leftRet.index;

        //do right, new start
        TreeNodeAndIndex rightRet = deserializeHelper(parts, ++start);
        node.right = rightRet.node;
        start = rightRet.index;
        return new TreeNodeAndIndex(node, start);
    }


    private static class TreeNodeAndIndex {
        TreeNode node;
        int index;
        TreeNodeAndIndex(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

}
