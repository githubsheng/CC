import java.util.ArrayList;
import java.util.List;

public class BuildCastlesOnHillsAndValleys {

    public int solution(int[] A) {

        List<Integer> uniqueHeights = new ArrayList<>();
        for (int i = 1; i < A.length; i++) {
            if(A[i] != A[i - 1]) {
                uniqueHeights.add(A[i]);
            }
        }

        //flat ground, just build 1 castle.
        if(uniqueHeights.size() == 0) return 1;

        uniqueHeights.add(0, Integer.MIN_VALUE);
        uniqueHeights.add(Integer.MIN_VALUE);
        //try to find hills
        int hillCounts = 0;
        for (int i = 1; i < uniqueHeights.size() - 1; i++) {
            if(uniqueHeights.get(i) > uniqueHeights.get(i + 1) && uniqueHeights.get(i) > uniqueHeights.get(i -1)) hillCounts++;
        }

        uniqueHeights.set(0, Integer.MAX_VALUE);
        uniqueHeights.set(uniqueHeights.size() - 1, Integer.MAX_VALUE);
        int valleysCount = 0;
        for (int i = 1; i < uniqueHeights.size() - 1; i++) {
            if(uniqueHeights.get(i) < uniqueHeights.get(i + 1) && uniqueHeights.get(i) < uniqueHeights.get(i -1)) valleysCount++;
        }

        return hillCounts + valleysCount;
    }

    public static void main(String[] args) {
        BuildCastlesOnHillsAndValleys sol = new BuildCastlesOnHillsAndValleys();
//        int count = sol.solution(new int[]{2, 2, 3, 4, 3, 3, 2, 2, 1, 1, 2, 5});
        int count = sol.solution(new int[]{-3});
        //2, 3, 4, 3, 2, 1, 2, 5
        System.out.println(count);
    }

}
