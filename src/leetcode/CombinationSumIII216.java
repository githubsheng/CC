package leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII216 {

    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> buffer = new ArrayList<>();

        _dfsAndBt(1, buffer, n, k);

        return ret;
    }


    private void _dfsAndBt(int startIdx, List<Integer> buffer, int sum, int expectedSize) {

        if(sum == 0 && buffer.size() == expectedSize) {
            ret.add(new ArrayList<Integer>(buffer));
            return;
        }

        for(int i = startIdx; i < 10 && i <= sum; i++) {

            //apply elem
            buffer.add(i);

            _dfsAndBt(i + 1, buffer, sum - i, expectedSize);

            //back tracking, may need to do in another way. todo:
            buffer.remove(buffer.size() - 1);

            //go on apply other elem
        }
    }


}
