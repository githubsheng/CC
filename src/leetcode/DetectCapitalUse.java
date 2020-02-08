package leetcode;

/**
 * Created by wangsheng on 29/6/18.
 */
public class DetectCapitalUse {

    public boolean detectCapitalUse(String word) {
        String allUpper = word.toUpperCase();
        if (word.equals(allUpper)) return true;
        String allLower = word.toLowerCase();
        if (word.equals(allLower)) return true;
        return word.equals(allUpper.substring(0, 1) + allLower.substring(1));
    }

}
