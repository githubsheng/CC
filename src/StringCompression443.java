/**
 * Created by wangsheng on 2/12/18.
 */
public class StringCompression443 {

    public int compress(char[] chars) {

        if(chars.length < 2) return chars.length;

        int writePointer = 0;
        char prev = chars[0];
        char c = 'a';
        int count = 1;

        for(int i = 1, l = chars.length; i < l; i++) {
            c = chars[i];
            if(c == prev) {
                count++;
            } else {
                chars[writePointer] = prev;
                writePointer++;

                if(count > 1) {
                    String s = String.valueOf(count);
                    for(int si = 0; si < s.length(); si++) {
                        chars[writePointer] = s.charAt(si);
                        writePointer++;
                    }
                }

                prev = c;
                count = 1;
            }
        }

        chars[writePointer] = c;
        writePointer++;

        if(count > 1) {
            String s = String.valueOf(count);
            for(int si = 0; si < s.length(); si++) {
                chars[writePointer] = s.charAt(si);
                writePointer++;
            }
        }

        return writePointer;
    }

}
