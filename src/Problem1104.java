import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem1104 {

    public List<Integer> pathInZigZagTree(int label) {
        int level = (int) (Math.log(label) / Math.log(2));

        List<Integer> paths = new ArrayList<>();
        int n = getReversed(label, level);
        while(n != 0) {
            paths.add(n);
            n /= 2;
        }
        Collections.reverse(paths);
        for(int i = 0; i < paths.size(); i++) {
            //i is level.
            int p = paths.get(i);
            paths.set(i, getReversed(p, i));
        }

        return paths;
    }

    private int getReversed(int n, int level){
        if(level % 2 == 0) return n;
        int min = (int)Math.pow(2, level);
        int max = min * 2 - 1;
        return max - n + min;
    }

    public static void main(String[] args) {
        Problem1104 sol = new Problem1104();
        List<Integer> ret = sol.pathInZigZagTree(1);
        for(int i : ret) {
            System.out.println(i);
        }
    }

}
