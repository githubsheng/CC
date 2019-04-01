import java.util.Arrays;

public class SumofSubsequenceWidths891 {

    private static final int MOD = 1000000007;

    public int sumSubseqWidths(int[] A) {
        int total = 0;
        Arrays.sort(A);

        for(int i = 0; i < A.length; i++) {
            for(int j = i; j < A.length; j++) {
                total +=helper(A, i, j);
            }
        }

        //total 这里可能也会溢出
        return total % MOD;
    }


    private int helper(int[] sorted, int start, int end) {
        if(start == end) return 0;
        if(start - end == 1) return sorted[end] - sorted[start];

        int subsequenceCount = (int)Math.pow(2, end - start -1);
        int width = sorted[end] - sorted[start];

        //此处溢出
        return width * subsequenceCount;
        //以下无用
        //return ((width % MOD) * (subsequenceCount % MOD)) % MOD;
    }

    public static void main(String[] args) {
        SumofSubsequenceWidths891 sol = new SumofSubsequenceWidths891();
        int ret = sol.sumSubseqWidths(new int[]{5,69,89,92,31,16,25,45,63,40,16,56,24,40,75,82,40,12,50,62,92,44,67,38,92,22,91,24});
        System.out.println(ret);
    }

}
