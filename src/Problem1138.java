import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Problem1138 {

    private String[] board = new String[]{"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};
    private int complete;
    private String target;
    private Set<Scout> scouts = new HashSet<>();
    private LinkedList<Scout> ctrl = new LinkedList<>();

    public String alphabetBoardPath(String target) {

        if(target.isEmpty()) return "";

        this.target = target;
        complete = target.length() - 1;
        ctrl.add(new Scout(-1, -1, -1, ""));

        while(!ctrl.isEmpty()) {
            Scout curr = ctrl.removeFirst();
            int r = curr.pr;
            int c = curr.pc;

            if(r == -1 && c == -1) {
                String ret = move(curr, 0, 0, "");
                if(ret != null) return ret;
            } else {
                //next char in target is the same as what the scout is looking at.
                //so the scout remains at the same position rather than tring to move around.
                if (board[r].charAt(c) == target.charAt(curr.found + 1)) {
                    String ret = move(curr, r, c, "");
                    if (ret != null) return ret;
                } else {
                    //move up, down, left, and right.
                    String ret = move(curr, r - 1, c, "U");
                    if(ret != null) return ret;

                    ret = move(curr, r + 1, c, "D");
                    if(ret != null) return ret;

                    ret = move(curr, r, c - 1, "L");
                    if(ret != null) return ret;

                    ret =  move(curr, r, c + 1, "R");
                    if(ret != null) return ret;
                }
            }

        }

        return "";
    }

    private String move(Scout curr, int nr, int nc, String direction) {

        if(nr < 0 || nr >= 6) return null;
        String row = board[nr];
        if(nc >= row.length() || nc < 0) return null;

        char next = board[nr].charAt(nc);
        Scout s;
        String path = curr.path + direction;
        if(next == target.charAt(curr.found + 1)) {
            s = new Scout(curr.found + 1, nr, nc, path + "!");
            if (s.found == complete) return s.path;
        } else {
            s = new Scout(curr.found, nr, nc, path);
        }

        if(scouts.contains(s)) return null;
        ctrl.addLast(s);
        scouts.add(s);
        return null;
    }

    private static class Scout {
        int found;
        int pr;
        int pc;
        String path;
        Scout(int found, int pr, int pc, String path) {
            this.found = found;
            this.pr = pr;
            this.pc = pc;
            this.path = path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Scout scout = (Scout) o;

            if (found != scout.found) return false;
            if (pr != scout.pr) return false;
            return pc == scout.pc;
        }

        @Override
        public int hashCode() {
            int result = found;
            result = 31 * result + pr;
            result = 31 * result + pc;
            return result;
        }
    }

    public static void main(String[] args) {
        Problem1138 sol = new Problem1138();
        String ret = sol.alphabetBoardPath("leetcode");
        System.out.println(ret);
    }

}
