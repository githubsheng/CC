import references.output.OutputUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem957 {

    private Map<Integer, Integer> prisonInDay = new HashMap<>();

    public int[] prisonAfterNDays(int[] _cells, int N) {
        int cells = convertToInt(_cells);
        int length = _cells.length;
        int days = 0;
        while(days < N) {
            days++;
            cells = prisionNextDay(cells, length);
            if(prisonInDay.containsKey(cells)) {
                int lastTime = prisonInDay.get(cells);
                int cycle = days - lastTime;
                int times = (N - lastTime) / cycle;
                days = lastTime + cycle * times;
                System.out.println(days);
            } else {
                prisionNextDay(cells, days);
                prisonInDay.put(cells, days);
            }
        }
        return convertToArray(cells, length);
    }

    private int prisionNextDay(int cells, int length) {
        int ret = cells;
        for (int i = 1; i < length - 1; i++) {
            //left
            int mask = 1 << (i - 1);
            boolean isLeftOccupied = (cells & mask) > 0;

            //right
            mask = 1 << (i + 1);
            boolean isRightOccupied = (cells & mask) > 0;
            //decide self

            if((isLeftOccupied && isRightOccupied) || (!isLeftOccupied && !isRightOccupied)) {
                ret |= (1 << i);
            } else {
                ret &= ~(1 << i);
            }
        }
        //set first day to 0
        ret &= ~1;
        //set last day to 0
        ret &= ~(1 << (length - 1));
        return ret;
    }

    private int convertToInt(int[] cells) {
        int ret = 0;
        for (int i = 0; i < cells.length; i++) {
            if(cells[i] == 1) {
                ret |= 1 << i;
            }
        }
        return ret;
    }

    private int[] convertToArray(int cells, int length) {
        int[] ret = new int[length];
        for (int i = 0; i < length; i++) {
            int mask = 1 << i;
            boolean isOccupied = (cells & mask) > 0;
            if(isOccupied) {
                ret[i] = 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Problem957 sol = new Problem957();
        int[] ret = sol.prisonAfterNDays(new int[]{0,1,0,1,1,0,0,1}, 10000);
        String s = Arrays.toString(ret);
        System.out.println(s);
    }


}
