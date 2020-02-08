package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*

You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.



Example 1:

Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]


Constraints:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.


 */
public class ReorderDataInLogFiles937 {

    public String[] reorderLogFiles(String[] logs) {
        int size = logs.length;
        Map<String, String> identifiers = new HashMap<>();
        Map<String, String> contents = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String log = logs[i];
            String[] parts = log.split(" ", 2);
            identifiers.put(log, parts[0]);
            contents.put(log, parts[1]);
        }

        Arrays.sort(logs, (o1, o2) -> {
            String c1 = contents.get(o1);
            String c2 = contents.get(o2);
            boolean isC1Digit = Character.isDigit(c1.charAt(0));
            boolean isC2Digit = Character.isDigit(c2.charAt(0));
            if (isC1Digit && isC2Digit) return 0;
            if (isC1Digit) return 1;
            if (isC2Digit) return -1;
            //both are string
            if (c1.equals(c2)) return identifiers.get(o1).compareTo(identifiers.get(o2));
            return c1.compareTo(c2);
        });

        return logs;
    }

}
