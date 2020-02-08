package leetcode;

import java.util.*;

//19:15
public class Problem472 {

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        if(words.length == 0) return Collections.emptyList();
        Set<String> dict = new HashSet<>();
        List<String> ret = new ArrayList<>();

        Arrays.sort(words, Comparator.comparingInt(String::length));

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if(word.length() == 0) continue;
            if(canBeFormed(word, dict)) ret.add(word);
            dict.add(word);
        }

        return ret;
    }

    private static boolean canBeFormed(String word, Set<String> dict) {
        if(dict.contains(word)) return true;
        for (int i = 0; i < word.length(); i++) {
            String prefix = word.substring(0, i + 1);
            if(dict.contains(prefix)) {
                boolean ret = canBeFormed(word.substring(i + 1), dict);
                if (ret) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Problem472.findAllConcatenatedWordsInADict(new String[]{"cat","dog","catdog"});

    }

}
