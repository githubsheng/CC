package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem224 {

    public int basicCal(String s) {
        s = s.replaceAll(" ", "");
        char[] arr = s.toCharArray();
        return eval(arr, 0)[0];
    }


    //evaluates an expression inside brackets. like so: (1+2)
//int[0] is the result, int[1] is the index, pointing beyond the last char of the exp, or closing )
    private int[] eval(char[] arr, int startIdx) {
        int i = startIdx;
        int prevNum = 0; // prev number before current symbol
        char prevOpt = 0; // operator before prev number
        Deque<Integer> stack = new LinkedList<>();

        //terminates when reaches the end or encounters )
        while(i < arr.length) {
            char c = arr[i];
            if(c == ')') break;

            if(Character.isDigit(c)) {
                prevNum *= 10;
                prevNum += c - '0';
            } else if (c == '(') {
                //recursively cal whats inside brackets
                int[] ret = eval(arr, i + 1);
                prevNum = ret[0];
                i = ret[1];
            } else {
                //c is an operator
                if(stack.isEmpty()){
                    stack.push(prevNum);
                } else {
                    int prevPrevNum = stack.poll();
                    int r = math(prevPrevNum, prevOpt, prevNum);
                    stack.push(r);
                }
                prevOpt = c; // c is remembered as prevOpt
                prevNum = 0; // reset and will be evaluated later
            }
            i++;
        }
        //finally, we have two cases:
        //num opt num in stack. eg (1 + 2 + 4), in this case, 3 in stack, prevNum is 4
        //nothing inside the stack eg ((1 + 2)), in this case, prevNum is 3
        int r = stack.isEmpty() ? prevNum : math(stack.poll(), prevOpt, prevNum);
        return new int[]{r, i};
    }

    private int math(int op1, char opt, int op2) {
        switch(opt) {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            default:
                throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        Problem224 sol = new Problem224();
        int ret = sol.basicCal("1+2+3");
        System.out.println(ret);
        ret = sol.basicCal("1 - ( 4+ 5 - 10)");
        System.out.println(ret);
    }
}
