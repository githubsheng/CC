package indeed;

/**
 * https://leetcode.com/problems/find-the-celebrity/
 */
public class FindCelebrity {

    int[][] m = new int[][]{
            {1, 1, 0},
            {0, 1, 1},
            {0, 1, 1}
    };

    public int findCelebrity(int n) {
        int candidate = 0;
        int prevCandidate = -1;

        for(int i = 0; i < n; i++) {
            if(knows(candidate, i)) {
                prevCandidate = candidate;
                candidate = i;
            }
        }

        //check if everyone knows candidate
        //we already know prev candidate knows candidate, so skip him
        for(int i = 0; i < n; i++) {
            if(i == candidate || i == prevCandidate) continue;
            if(!knows(i, candidate)) return -1;
        }

        //check that candidate does not know anyone
        //we already know that candidate does not know anyone n > candidate
        for(int i = 0; i < candidate; i++) {
            if(knows(candidate, i)) return -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        FindCelebrity sol = new FindCelebrity();
        sol.findCelebrity(3);

    }

    private boolean knows(int a, int b) {
        return m[a][b] == 1;
    }

}
