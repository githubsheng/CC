import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static references.output.OutputUtil.printList;

/**
 * Created by wangsheng on 9/12/18.
 */
public class PascalsTriangleII119 {

    public List<Integer> getRow(int rowIndex) {

        if(rowIndex == 0) return Collections.singletonList(1);
        if(rowIndex == 1) {
            List<Integer> ret = new ArrayList<>(2);
            ret.add(1);
            ret.add(1);
            return ret;
        }

        int[] prevRow = new int[34];
        int[] nextRow = new int[34];

        int idx = 2;
        prevRow[0] = 1;
        prevRow[1] = 1;

        while(true) {

            int rowLength = idx + 1;
            nextRow[0] = 1;
            nextRow[idx] = 1;
            for(int i = 1; i < idx; i++) {
                nextRow[i] = prevRow[i - 1] + prevRow[i];
            }

            if(idx == rowIndex) break;


            idx++;
            int[] _temp = prevRow;
            prevRow = nextRow;
            nextRow = _temp;
            for(int i = 0; i < 34; i++) {
                nextRow[i] = 0;
            }

        }

        List<Integer> ret = new ArrayList<>(34);
        for(int i = 0; i < rowIndex + 1; i++) {
            ret.add(nextRow[i]);
        }

        return ret;
    }

    public static void main(String[] args) {
        PascalsTriangleII119 sol = new PascalsTriangleII119();
        List<Integer> ret = sol.getRow(33);
        printList(ret);
    }

}
