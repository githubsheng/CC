import java.util.LinkedList;

/**
 * Created by wangsheng on 2/12/18.
 */
public class ValidParentheses20 {

    public boolean isValid(String s) {

        if(s.isEmpty()) return true;
        if(s.length() < 2) return false;

        LinkedList<Character> stack = new LinkedList<>();
        stack.push(s.charAt(0));

        for(int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c =='{') {
                stack.addLast(c);
            } else if (stack.size() == 0) {
                return false;
            } else if (c == ')') {
                if(stack.peekLast() != '(') return false;
                stack.removeLast();
            } else if (c == ']') {
                if(stack.peekLast() != '[') return false;
                stack.removeLast();
            } else {
                if(stack.peekLast() != '{') return false;
                stack.removeLast();
            }
        }

        return stack.size() == 0;
    }

    public static void main(String[] args) {
        String s = "";
        ValidParentheses20 solution = new ValidParentheses20();
        System.out.println(solution.isValid(s));
    }

}
