import java.util.Arrays;

public class Problem1278 {



    public int palindromePartition(String s, int k) {
        int[][] cache = new int[s.length() + 1][k + 1];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
        return helper(s, k, cache);
    }

    int helper(String str, int k, int[][] cache) {
        int strLen = str.length();

        if(cache[strLen][k] != -1) return cache[strLen][k];


        if(k == 1) {
            int ret = change(str);
            cache[strLen][k] = ret;
            return ret;
        }
        if(strLen == k) return 0;
        int min = strLen;
        for (int i = 1; i < strLen; i++) {
            String prefix = str.substring(0, i);
            int remainLen = strLen - prefix.length();
            if(remainLen < k - 1) break;
            int c = change(prefix);
            String remain = str.substring(i);
            c+= helper(remain, k - 1, cache);
            min = Math.min(min, c);
        }
        cache[strLen][k] = min;
        return min;
    }

    int change(String s)
    {
        if(s.length() == 1) return 0;
        if(s.length() == 2 && s.charAt(0) != s.charAt(1)) return 1;

        int n = s.length();
        int cc = 0;

        for(int i = 0; i < n/2; i++)
        {
            if(s.charAt(i) == s.charAt(n - i - 1))
                continue;
            cc += 1;
        }

        return cc;
    }

    public static void main(String[] args) {
        Problem1278 sol = new Problem1278();
        int ret = sol.palindromePartition("aabbc", 3);
        System.out.println(ret);
    }

}
