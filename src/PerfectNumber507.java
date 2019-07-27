public class PerfectNumber507 {

    public boolean checkPerfectNumber(int num) {

        if(num == 1) return false;

        int sum = 1;
        double limit = Math.sqrt(num);

        for (int i = 2; i < limit; i++) {
            if(num % i == 0) {
                sum += i;
                sum += num / i;
            }
        }

        if(sum == num) return true;

        return false;
    }

    public static void main(String[] args) {
        PerfectNumber507 sol = new PerfectNumber507();
        boolean ret = sol.checkPerfectNumber(28);
        System.out.println(ret);
    }

}
