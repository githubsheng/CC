import java.util.Arrays;

public class Problem1256 {

    public String encode(int num) {
        if (num == 0) return "";
        int n = 0;
        int v = 0;
        int next = 1;

        //in this case, n starts with 3.
        while(true) {
            int t = next * 2 - 1;
            if(t <= num) {
                n = t;
                next *= 2;
            } else {
                break;
            }
        }

        next /= 2;

        v = num - n;
        int len = Integer.toBinaryString(next).length();
        String tr = Integer.toBinaryString(v);
        int diff = len - tr.length();
        char[] p = new char[diff];
        Arrays.fill(p, '0');
        return new String(p) + tr;
    }

    public static void main(String[] args) {
        Problem1256 sol = new Problem1256();
        String ret;
        ret = sol.encode(1);
        System.out.println(ret);
        ret = sol.encode(3);
        System.out.println(ret);
        ret = sol.encode(107);
        System.out.println(ret);
//        ret = sol.encode(3);
//        System.out.println(ret);
//        ret = sol.encode(2);
//        System.out.println(ret);
    }
}
