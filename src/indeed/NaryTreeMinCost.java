package indeed;

import java.util.*;

public class NaryTreeMinCost {

    public List<TreeNode> findMinCostFromRootToLeaf(TreeNode root) {
        if(root == null) return Collections.emptyList();
        Path currentPath = new Path();
        Path result = new Path();
        result.totalCost = Integer.MAX_VALUE;
        helper(root, currentPath, result);
        return result.path;
    }

    private void helper(TreeNode node, Path path, Path result) {
        path.addNode(node);
        //if node is leaf
        if(isLeaf(node) && path.totalCost < result.totalCost) {
            result.copyFromPath(path);
        } else {
            for(TreeNode c : node.children) {
                helper(c, path, result);
            }
        }
        path.backTrack();
    }

    private boolean isLeaf(TreeNode node) {
       return  node.children == null || node.children.isEmpty();
    }

    private static class Path {
        List<TreeNode> path = new ArrayList<>();
        int totalCost;

        void addNode(TreeNode node) {
            path.add(node);
            totalCost += node.cost;
        }

        void copyFromPath(Path other) {
            path = new ArrayList<>(other.path);
            totalCost = other.totalCost;
        }

        void backTrack() {
            TreeNode last = path.remove(path.size() - 1);
            totalCost -= last.cost;
        }
    }

    private static class TreeNode {
        int cost;
        List<TreeNode> children = new ArrayList<>();
        TreeNode(int _cost) {
            cost = _cost;
        }
    }

    public static void main(String[] args) {
        NaryTreeMinCost sol = new NaryTreeMinCost();

        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.children.add(n2);
        root.children.add(n3);

        TreeNode n21 = new TreeNode(1);
        n2.children.add(n21);
        n21.children.add(new TreeNode(1));

        n3.children.add(new TreeNode(99));

        List<TreeNode> ret = sol.findMinCostFromRootToLeaf(root);
        for(TreeNode n : ret) {
            System.out.println(n.cost);
        }
    }

}


