import data.structure.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//11:38 - 12:05 ~ 27min
public class Problem545 {

        private TreeNode leftMost = null;
        private TreeNode rightMost = null;
        private TreeNode root = null;

        public List<Integer> boundaryOfBinaryTree(TreeNode root) {

            if(root == null) return Collections.emptyList();
            this.root = root;
            List<Integer> ret = new ArrayList<>();
            ret.add(root.val);

            List<Integer> left = getLeftBoundary(root);
            List<Integer> right = getRightBoundary(root);
            List<Integer> leaves = getLeaves(root);

            for(int i : left) ret.add(i);
            for(int i : leaves) ret.add(i);
            for(int i : right) ret.add(i);

            return ret;

        }

        private List<Integer> getLeftBoundary(TreeNode root) {
            List<Integer> ret = new ArrayList<>();
            //left starts with node.left
            TreeNode node = root.left;
            while(node != null) {
                ret.add(node.val);
                leftMost = node;
                if(node.left != null) {
                    node = node.left;
                } else if (node.right != null) {
                    node = node.right;
                } else {
                    node = null;
                }
            }
            return ret;
        }

        private List<Integer> getRightBoundary(TreeNode root) {
            List<Integer> ret = new ArrayList<>();
            TreeNode node = root.right;
            while(node != null) {
                ret.add(node.val);
                rightMost = node;
                if(node.right != null) {
                    node = node.right;
                } else if (node.left != null) {
                    node = node.left;
                } else {
                    node = null;
                }
            }
            Collections.reverse(ret);
            return ret;
        }

        private List<Integer> getLeaves(TreeNode root) {
            List<Integer> ret = new ArrayList<>();
            preOrder(root, ret);
            return ret;
        }

        private void preOrder(TreeNode node, List<Integer> ret) {
            if(node == null) return;
            if(node.left == null && node.right == null) {
                //a leave
                if (node != leftMost && node != rightMost && node != root) {
                    ret.add(node.val);
                }
                return;
            }
            preOrder(node.left, ret);
            preOrder(node.right, ret);
        }

}
