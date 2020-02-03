package contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Q3 {

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = br.lines().collect(Collectors.toList());

        int sum = 0;
        for(String s : input) {
            if(s.isEmpty()) continue;
            if(s.charAt(0) == '-') s = s.substring(1);
            if(s.isEmpty()) continue;

            boolean isNum = true;
            for(char c : s.toCharArray()) {
                if(c < '0' || c > '9') {
                    isNum = false;
                    break;
                }
            }

            if(isNum) {
                int i  = Math.abs(Integer.parseInt(s));
                sum += i;
            }
        }

        System.out.println(sum);
    }

}
