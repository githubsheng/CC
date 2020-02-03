class Problem227 {

    public int calculate(String s) {
        s += ')'; //trick to allow us trigger one last merge or mul
        char[] arr = s.toCharArray();
        return eval(arr, 0)[0];
    }

    private int[] eval(char[] arr, int startIdx) {
        int i = startIdx;

        int num = 0;
        char sign = '+'; //by default, first number has + in front.
        int ret = 0;
        int last = 0;

        while(i < arr.length) {
            char c = arr[i];
            if(c == ' ') {
                //do nothing, i will be incremented later.
            } else if(Character.isDigit(c)) {
                num *= 10;
                num += c - '0';
            } else if (c == '(') {
                int[] info = eval(arr, i + 1); //definitely ends with )
                i = info[1];
                num = info[0];
            } else {//has to be a sign, or ), that allows us to tell the value of num

                //merge sign with num;
                if(sign == '+' || sign == '-') {
                    last = merge(sign, num);
                } else { //has to be * or /
                    ret -= last; //undo
                    last = mul(sign, last, num);
                }

                ret += last;

                num = 0;
                sign = c;

                if(c == ')') break;
            }

            i++;
        }

        return new int[]{ret, i}; //return the eval result, and ) position. if exp does not end with ), i means nothing
    }

    private int merge(char sign, int num) {
        return sign == '-' ? -num : num;
    }

    private int mul(char operator, int op1, int op2) {
        if(operator == '*') return op1 * op2;
        return op1 / op2;
    }
}