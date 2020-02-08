package leetcode;

public class Problem754 {

    public int reachNumber(int target) {

        if(target == 0) return 0;
        if(target < 0) target = -target;

        int r = 0;
        int steps = 0;

        while (r < target) {
            steps++;
            r += steps;
        }

        int diff = r - target;

        if(diff % 2 == 0) return steps;
        if(steps % 2 == 0) return steps + 1;
        return steps + 2;
    }


    public static void main(String[] args) {
        Problem754 sol = new Problem754();
        int ret = sol.reachNumber(-2);
        System.out.printf(String.valueOf(ret));
    }

}
