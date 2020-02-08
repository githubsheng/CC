package leetcode;

import java.util.*;

/**
 * Created by wangsheng on 4/12/18.
 */
public class CombinationSumII40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) return Collections.emptyList();
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        helper(candidates, 0, target, new ArrayList<>(), ret);
        return ret;
    }

    private void helper(int[] candidates, int idx, int target, List<Integer> temp, List<List<Integer>> ret) {

        if(target == 0) ret.add(new ArrayList<>(temp));
        if(target < 0) return;

        Set<Integer> starts = new HashSet<>();

        for(int i = idx; i < candidates.length; i++) {
            int start = candidates[i];

            if(starts.contains(start)) continue;
            starts.add(start);


            temp.add(start);
            helper(candidates, i + 1, target - start, temp, ret);
            temp.remove(temp.size() - 1);
        }
    }

}
