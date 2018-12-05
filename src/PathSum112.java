import data.structure.TreeNode;

/**
 * Created by wangsheng on 2/12/18.
 */
public class PathSum112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        return helper(root, 0, sum);
    }

    private boolean helper(TreeNode node, int now, int sum) {

        now += node.val;

        if(node.left == null && node.right == null) {
            return now == sum;
        } else {

            boolean ret = false;

            if(node.left != null) {
                ret = helper(node.left, now, sum);
            }

            if(ret) return ret;

            if(node.right != null) {
                return helper(node.right, now, sum);
            }

            return ret;

        }
    }



}
