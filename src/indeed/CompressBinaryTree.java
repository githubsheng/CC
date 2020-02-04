package indeed;

import data.structure.TreeNode;
import java.util.*;

public class CompressBinaryTree {

    public int[][] compress(TreeNode root) {

        Map<TreeNode, Integer> m = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        m.put(root, 1);
        List<Integer> vals = new ArrayList<>();
        List<Integer> idxs = new ArrayList<>();

        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            int idx = m.get(n);

            vals.add(n.val);
            idxs.add(idx);

            if(n.left != null) {
                m.put(n.left, idx * 2);
                q.offer(n.left);
            }

            if(n.right != null) {
                m.put(n.right, idx * 2 + 1);
                q.offer(n.right);
            }
        }

        return new int[][]{convert(idxs), convert(vals)};
    }

    private int[] convert(List<Integer> list) {
        int[] ret = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        TreeNode ll = new TreeNode(4);
        left.left = ll;
        TreeNode lr = new TreeNode(5);
        left.right = lr;
        CompressBinaryTree sol = new CompressBinaryTree();
        int[][] ret = sol.compress(root);
        for(int i : ret[0]) {
            System.out.println(i);
        }
        System.out.println("----");
        for(int i : ret[1]) {
            System.out.println(i);
        }
    }


}
