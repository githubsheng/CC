import references.output.OutputUtil;

public class Problem927 {

    private int[] A;
    private int[] nextOnes;

    public int[] threeEqualParts(int[] A) {

        if(A.length == 0) return new int[]{-1, -1};
        int c = 0;
        for(int x: A) {
            if(x == 1) c++;
        }
        if(c % 3 != 0) return new int[]{-1, -1};

        this.A = A;



        buildNextOnes();
        int lastIdx = A.length - 1;

        int p1es = nextOnes[0];
        if(p1es == -1) return new int[]{0, lastIdx};

        for (int i = 0; i < A.length; i++) {
            int p1l = Math.max(i - p1es + 1, 0);
            int p2s = i + 1;
            if(p2s > lastIdx) continue;
            int p2es = nextOnes[p2s];
            if(p2es == -1) {
                //means p2 and p3 will be 0 length
                //but if it goes here... p1 is not 0
                return new int[]{-1, -1};
            }
            int p2e = p2es + p1l - 1;
            int p3s = p2e + 1;
            if(p3s > lastIdx) continue;
            int p3es = nextOnes[p3s];
            if(p3es == -1) {
                //means p3 is all 0, but if it goes here then p1 and p2 is not 0
                return new int[]{-1, -1};
            }
            int p3l = lastIdx - p3es + 1;
            if(p3l == p1l) {
                if(compare(p1es, p2es, p3es)) {
                    return new int[]{i, p3s};
                }
            }
        }

        return new int[]{-1, -1};
    }

    private boolean compare(int es1, int es2, int es3) {
        for (int i = 0; es3 + i < A.length; i++) {
            if(A[es1 + i] != A[es2 + i] || A[es1 + i] != A[es3 + i])
                return false;

        }
        return true;
    }

    private void buildNextOnes(){
        nextOnes = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            nextOnes[i] = -1;
        }
        int stackBottom = -1;
        int stackTop = -1;
        for (int i = 0; i < A.length; i++) {
            int val = A[i];
            if(val == 0) {
                if (stackBottom == -1) {
                    stackBottom = i;
                }
                stackTop = i;
            } else {
                //val == 1
                if(stackBottom != -1) {
                    for (int j = stackBottom; j <= stackTop ; j++) {
                        nextOnes[j] = i;
                    }
                    stackBottom = -1;
                    stackTop = -1;
                }
                nextOnes[i] = i;
            }
        }
    }

    public static void main(String[] args) {
        Problem927 sol = new Problem927();
        int[] ret = sol.threeEqualParts(new int[] {0,0,0,0,0});
        OutputUtil.printArray(ret);
    }

}
