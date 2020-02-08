package leetcode;

import data.structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
889. Construct Binary Tree from Preorder and Postorder Traversal
medium
 */
public class Problem889 {

    private int[] pre;
    private int[] post;
    private Map<Integer, Integer> dict = new HashMap<>();

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;

        for (int i = 0; i < pre.length; i++) {
            dict.put(pre[i], i);
        }

        return this.helper(0, pre.length - 1, 0, post.length - 1);
    }

    private TreeNode helper(int preStart, int preEnd, int postStart, int postEnd) {

        if(pre.length == 0) return null;

        if(preEnd == preStart) return new TreeNode(pre[preStart]);

        int root = pre[preStart];
        TreeNode rootNode = new TreeNode(root);
        int left = pre[preStart + 1];
        int right = post[postEnd - 1];



        if(left == right) {
            //has only one child, treat it as left (or right, either way is fine)
            int leftPreStart = preStart + 1;
            int leftPostEnd = postEnd - 1;
            rootNode.left = helper(leftPreStart, preEnd, postStart, leftPostEnd);
        } else {
            int leftPreStart = preStart + 1;
            int leftPreEnd = dict.get(right) - 1;
            int leftPostStart = postStart;
            int leftLength = leftPreEnd - preStart;
            int leftPostEnd = leftPostStart + leftLength - 1;

            rootNode.left = helper(leftPreStart, leftPreEnd, leftPostStart, leftPostEnd);

            int rightPreStart = leftPreEnd + 1;
            int rightPreEnd = preEnd;
            int rightPostStart = postStart + leftLength;
            int rightPostEnd = postEnd - 1;
            rootNode.right = helper(rightPreStart, rightPreEnd, rightPostStart, rightPostEnd);
        }

        return rootNode;
    }

    public static void main(String[] args) {
        Problem889 sol = new Problem889();
        int[] pre = new int[]{1,2,4,5,3,6,7};
        int[] post = new int[]{4,5,2,6,7,3,1};
        TreeNode ret = sol.constructFromPrePost(pre, post);
        System.out.println(1);

        int[] pre1 = new int[]{1,3,6,7};
        int[] post1 = new int[]{6,7,3,1};
        TreeNode ret2 = sol.constructFromPrePost(pre1, post1);
        System.out.println(2);
    }


}
