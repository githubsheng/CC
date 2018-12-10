import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangsheng on 9/12/18.
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {

        if(s.isEmpty()) return s;

        int[] lastAppearance = new int[26];
        boolean[] included = new boolean[26];

        for(int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 97;
            lastAppearance[idx] = i;
        }


        LinkedList<Character> stack = new LinkedList<>();

        for(int i = 0; i < s.length(); i++) {

            Character c = s.charAt(i);
            int cidx = c - 97;

            while(true) {

                if(stack.isEmpty()) {
                    stack.add(c);
                    included[cidx] = true;
                    break;
                }

                if(included[cidx]) break;

                Character last = stack.peekLast();
                int com = last.compareTo(c);
                if(com < 0) {
                    //bigger than last, add it
                    stack.add(c);
                    included[cidx] = true;
                    break;
                } else if (com > 0) {

                    int laIdx = last -97;
                    int t = lastAppearance[laIdx];

                    //if last can be inserted later
                    if(t > i) {
                        stack.removeLast();
                        included[laIdx] = false;
                        //c will be added later.
                    } else { //not removable..
                        stack.add(c);
                        included[cidx] = true;
                        break;
                    }

                } else { //com == 0
                    break;
                }

            }

        }

        StringBuilder sb = new StringBuilder();
        for(Character c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters sol = new RemoveDuplicateLetters();
        String ret = sol.removeDuplicateLetters("bcabc");
        System.out.println(ret);
    }

}
