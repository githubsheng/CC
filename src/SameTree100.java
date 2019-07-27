import data.structure.TreeNode;

public class SameTree100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p != null && q != null) {
            if(p.val != q.val) return false;
            boolean isLeftSame = isSameTree(p.left, q.left);
            if(!isLeftSame) return false;

            boolean isRightSame = isSameTree(p.right, q.right);
            return isRightSame;
        }
        return false;
    }

    public static void main(String[] args){

    }

}
