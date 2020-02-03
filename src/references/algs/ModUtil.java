package references.algs;

public class ModUtil {

    private int getMode(int start, int end, int mod) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += i;
        }
        return sum % mod;
    }

    private int getMode2(int start, int end, int mod) {
        int ret = 0;
        for (int i = start; i < end; i++) {
            ret = (ret + i % mod) % mod;
        }
        return ret;
    }

    private boolean isEqual(int start, int end, int mod) {
        return getMode(start, end, mod) == getMode2(start, end, mod);
    }


    public static void main(String[] args) {
        ModUtil sol = new ModUtil();
        boolean ret = sol.isEqual(11, 19, 5);
        System.out.println(ret);
    }
}
