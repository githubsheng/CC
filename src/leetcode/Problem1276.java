package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Problem1276 {

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {

        int t = 4 * cheeseSlices - tomatoSlices;
        if(t % 2 != 0) return Collections.emptyList();
        int s = t / 2;
        int j = cheeseSlices - s;
        if(s < 0 || j < 0) return Collections.emptyList();
        return Arrays.asList(j, s);
    }

}
