package indeed;
import java.util.*;
public class ValidatePythonIndentation {

    public int validatePythonIndentation(List<String> lines) {
        /*


        breakIntoLines()
        loop lines, Stmt(line)

        stack

        Stmt
            +getInd
            +isCtrl

        for loop stmts
            peek -> push
            while loop -> pop stmt with greater ind
         */
        List<Stmt> sms = new ArrayList<>(lines.size());
        for(String line : lines) {
            String tr = line.trim();
            if(tr.length() == 0 || tr.startsWith("#")) continue;
            Stmt stmt = new Stmt(line);
            sms.add(stmt);
        }


        Deque<Stmt> stack = new LinkedList<>();

        if(sms.isEmpty()) return -1;
        if(sms.get(0).getInd() != 0) return 0; //first line needs to have 0 indentation

        stack.offer(sms.get(0));


        for(int i = 1; i < sms.size(); i++) {


            Stmt curr = sms.get(i);
            Stmt prev = stack.peek();
            boolean isAfterCtrBodyEnd = false;

            int prevInd = prev.getInd();
            int currInd = curr.getInd();

            while(prevInd > currInd) {


                stack.pop();
                isAfterCtrBodyEnd = true;
                prev = stack.peek();
                prevInd = prev.getInd();
            }

            if(isAfterCtrBodyEnd) {
                //if is after ctrl body, we need to match a ctrl condition, of same indentation
                if((!prev.isCtrl() || prevInd != currInd)) return i;
            } else if (prev.isCtrl()) {
                //not after a ctrl body, but after a ctrl condition, need to have greater indentation
                if(currInd <= prevInd) return i;
            } else {
                //not after a ctrl body / condition, need to have same indentation of statement above
                if(currInd != prevInd) return i;
            }

            stack.push(curr);

        }

        return -1;

    }


    private static class Stmt {

        int ind;
        boolean isCtrl; //is control statement condition ?

        Stmt(String line) {

            int w = 0;
            char[] arr = line.toCharArray();

            for(char c : arr) {

                if(c == ' ') {
                    w++;
                } else {
                    break;
                }
            }

            this.ind = w;


            String tl = line.trim();
            if(tl.charAt(tl.length() - 1) == ':') isCtrl = true;
        }

        int getInd(){
            return ind;
        }

        boolean isCtrl() {
            return isCtrl;
        }

    }


    public static void main(String[] args) {
        ValidatePythonIndentation sol = new ValidatePythonIndentation();

        for (int i = 1; i < 7; i++) {
            Scanner sc = new Scanner(ValidatePythonIndentation.class.getResourceAsStream("../input/" + i + ".txt"));
            List<String> lines = new ArrayList<>();

            while(sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            int errorLine  = sol.validatePythonIndentation(lines) + 1;
            System.out.println(errorLine);

        }
    }

}
