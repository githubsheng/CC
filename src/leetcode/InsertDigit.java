package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertDigit {

    public int solution(int i) {

        if(i == 0) return 50;

        boolean isNegative = i < 0;
        i = Math.abs(i);
        List<Integer> digits = getDigits(i);

        int insertPos = -1;

        for (int j = 0; j < digits.size(); j++) {
            if((!isNegative && digits.get(j) < 5) || (isNegative && digits.get(j) > 5)) {
                insertPos = j;
                break;
            }
        }
        if (insertPos == -1) {
            digits.add(5);
        } else {
            digits.add(insertPos, 5);
        }
        int abs = getNumber(digits);
        return isNegative ? abs * -1 : abs;
    }

    private int getNumber(List<Integer> digits) {
        int ret = 0;
        for(int i : digits) {
            ret *= 10;
            ret += i;
        }
        return ret;
    }

    private List<Integer> getDigits(int i){
        List<Integer> ret = new ArrayList<>();
        while(i > 0) {
            int digit = i % 10;
            ret.add(digit);
            i = i / 10;
        }
        Collections.reverse(ret);
        return ret;
    }

    public static void main(String[] args) {
        InsertDigit temp = new InsertDigit();
        int ret = temp.solution(-10);
        System.out.println(ret);
    }

}
