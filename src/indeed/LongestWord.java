package indeed;
import java.util.*;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary/
 */
public class LongestWord {


    public String longestWord(String[] words) {
        Arrays.sort(words);
        String max = "";
        Node root = new Node();
        for(String word : words) {
            if(!addWord(word, root)) continue;
            if(word.length() > max.length()) max = word;
        }
        return max;
    }

    private boolean addWord(String word, Node root) {

        char[] arr = word.toCharArray();
        Node search = root;
        int i = 0;

        while(i < arr.length) {
            char c = arr[i];
            Node n = search.getNextNode(c);
            if(n == null) {
                Node nn = new Node();
                search.setNextNode(nn, c);
                if(i == arr.length - 1) {
                    nn.isWord = true;
                    return true;
                } else {
                    return false;
                }
            } else {
                if(!n.isWord) return false;
                search = n;
                i++;
            }
        }

        //if the above while loop ends without returning
        //if means the word matches all existing node,
        //and all existing node has `isWord` set to true
        //it means this word has been added before.
        return true;

    }



    private static class Node {
        boolean isWord;
        Node[] nexts = new Node[26];
        Node getNextNode(char c) {
            int idx = c - 'a';
            return nexts[idx];
        }

        void setNextNode(Node node, char c) {
            int idx = c - 'a';
            nexts[idx] = node;
        }
    }

}
