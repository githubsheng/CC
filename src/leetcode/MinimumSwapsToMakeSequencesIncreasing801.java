package leetcode;

/*

We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

    Example:
    Input: A = [1,3,5,4], B = [1,2,3,7]
    Output: 1
    Explanation:
    Swap A[3] and B[3].  Then the sequences are:
    A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
    which are both strictly increasing.
    Note:

A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].

 */
public class MinimumSwapsToMakeSequencesIncreasing801 {

    private int failValue;

    public int minSwap(int[] a, int[] b) {
        failValue = Integer.MAX_VALUE;
        if(a.length == 1) return 0;

        int[][] memo = new int[a.length][2];

        for(int i = 0; i < memo.length; i++) {
            memo[i][0] = -1;
            memo[i][1] = -1;
        }

        int r1 = helper(a, b, false, 0, memo);
        int r2 = helper(a, b, true, 0, memo);

        return Math.min(r1, r2);
    }

    private int helper(int[] a, int[] b, boolean swap, int start, int[][] memo) {
        int swapidx = swap ? 1 : 0;
        int cached = memo[start][swapidx];
        if(cached != -1) return cached;

        if(start == a.length - 1) {
            return swap? 1 : 0;
        }

        int av = a[start];
        int bv = b[start];

        if(swap) {
            a[start] = bv;
            b[start] = av;
        }

        int nav = a[start + 1];
        int nbv = b[start + 1];

        int ret = failValue;

        if(a[start] < nav && b[start] < nbv) {
            int r1 = helper(a, b, false, start + 1, memo);
            ret = Math.min(ret, r1);
        }

        if(a[start] < nbv && b[start] < nav && nav != nbv) {
            int r2 = helper(a, b, true, start + 1, memo);
            ret = Math.min(ret, r2);
        }

        if(swap) {
            a[start] = av;
            b[start] = bv;
        }

        if(ret != failValue && swap)
            ret += 1;


        memo[start][swapidx] = ret;

        return ret;
    }

    public static void main(String[] args) {
        MinimumSwapsToMakeSequencesIncreasing801 sol = new MinimumSwapsToMakeSequencesIncreasing801();
        int[] a = new int[]{4,10,13,14,17,19,21,24,26,27,28,29,34,37,38,42,44,46,48,51,52,53,54,57,58,59,64,65,66,67,71,73,75,76,80,81,82,83,86,88,89,90,95,97,98,99,101,105,106,108,109,110,111,112,115,119,121,122,123,124,125,126,127,128,129,130,131,133,136,138,143,145,147,149,150,153,158,160,163,164,165,167,168,169,172,173,174,176,178,179,183,184,186,188,189,192,193,194,198,200};
        int[] b = new int[]{0,1,3,5,6,7,11,13,15,16,17,21,37,39,41,42,43,45,47,50,53,55,56,57,64,66,67,68,69,70,71,72,74,75,76,77,79,80,87,88,89,95,96,97,98,100,101,105,106,107,108,112,113,115,116,118,119,122,124,125,126,127,128,131,135,136,137,138,139,140,144,145,148,150,151,154,159,160,161,162,163,167,168,170,171,174,176,178,179,180,181,185,187,189,190,191,192,198,199,200};
        int ret = sol.minSwap(a, b);

        System.out.println(ret);
        int[] a1 = new int[]{1, 3, 5, 4};
        int[] b1 = new int[]{1, 2, 3, 7};
        int ret2 = sol.minSwap(a1, b1);


        System.out.println(ret2);
    }

}
