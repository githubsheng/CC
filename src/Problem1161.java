import data.structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem1161 {

    public int maxLevelSum(TreeNode root) {
        if(root == null) return 0;
        int max = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        int ret = level;


        while(!queue.isEmpty()) {
            int sum = 0;
            int size = queue.size();
            int count = 0;
            while(count < size) {
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                count++;
            }
            if(sum > max) {
                ret = level;
                max = sum;
            }
            level++;
        }

        return ret;
    }

}
