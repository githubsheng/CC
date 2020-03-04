package indeed;

import data.structure.TreeNode;
import references.output.OutputUtil;

import java.util.*;

public class CompressBinaryTree {

    public int[] compressCompleteTree(TreeNode root) {
        int[] compress = new int[walk(root, 1, null)];
        walk(root, 1, compress);
        return compress;
    }

    private int walk(TreeNode root, int index, int[] compress) {
        if(root == null) return 0;
        if(compress != null) compress[index - 1] = root.val;
        return 1 + walk(root.left, index * 2, compress) + walk(root.right, index * 2 + 1, compress);
    }

    //decompress a complete tree or a perfect tree.
    public TreeNode decompressCompleteTree(int[] compressed) {
        if(compressed.length == 0) return null;
        TreeNode[] nodes = new TreeNode[compressed.length + 1];
        nodes[1] = new TreeNode(compressed[0]);
        for(int i = 1; i < compressed.length; i++) {
            int idx = i + 1;
            TreeNode parent = nodes[idx / 2], current = new TreeNode(compressed[i]);
            if(idx % 2 == 0) parent.left = current; else parent.right = current;
            nodes[idx] = current;
        }
        return nodes[1];
    }


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

    private TreeNode decompress(int[] idxs, int[] vals) {

        Map<Integer, TreeNode> idxToNode = new HashMap<>();

        for (int i = 0; i < idxs.length; i++) {
            int idx = idxs[i];
            TreeNode node = new TreeNode(vals[i]);
            idxToNode.put(idx, node);
            if(i == 0) continue;
            TreeNode parent = idxToNode.get(idx / 2);
            if(idx % 2 == 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }

        //get the root.
        return idxToNode.get(1);
    }

    public static void main(String[] args) {
        CompressBinaryTree sol = new CompressBinaryTree();

        //this is a complete tree
        TreeNode cr = new TreeNode(1);
        cr.left = new TreeNode(2);
        cr.right = new TreeNode(3);
        cr.left.left = new TreeNode(4);
        cr.left.right = new TreeNode(5);
        cr.right.left = new TreeNode(6);

        int[] compressedComplete = sol.compressCompleteTree(cr);

        OutputUtil.printArray(compressedComplete);

        TreeNode ct = sol.decompressCompleteTree(compressedComplete);
        System.out.println("debug point to check new complete tree");

        //below is for sparse tree.....or none complete tree
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left =  new TreeNode(6);
        root.right.right = new TreeNode(7);
        int[][] ret = sol.compress(root);
        OutputUtil.printArray(ret[0]);
        OutputUtil.printArray(ret[1]);

        TreeNode newRoot = sol.decompress(ret[0], ret[1]);
        System.out.println("debug point to check new parse tree");
    }


}
