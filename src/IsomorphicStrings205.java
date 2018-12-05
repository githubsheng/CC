import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangsheng on 2/12/18.
 */
public class IsomorphicStrings205 {

    public boolean isIsomorphic(String s, String t) {

        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> reversedMap = new HashMap<>();

        for(int i = 0, l = s.length(); i < l; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            Character st = map.get(c1);
            Character ts = reversedMap.get(c2);

            if(st == null && ts == null) {
                map.put(c1, c2);
                reversedMap.put(c2, c1);
            } else if (st != null && st != c2) {
                return false;
            } else if (ts != null && ts != c1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String args[]) {
        IsomorphicStrings205 sol = new IsomorphicStrings205();
        sol.isIsomorphic("ab", "ca");
    }

}
