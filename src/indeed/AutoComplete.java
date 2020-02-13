package indeed;
import java.util.*;

public class AutoComplete {

    private List<String> dict;
    private Node root = new Node();
    public AutoComplete(List<String> dict) {
        this.dict = dict;
        for(String word : dict) {
            addWord(word);
        }
    }

    private void addWord(String word) {
        char[] arr = word.toCharArray();
        Node search = root;
        int i = 0;

        while(i < arr.length) {
            char c = arr[i];
            Node n = search.getNext(c);
            if(n == null) {
                n = new Node();
                search.putNext(n, c);
            }

            search = n;
            i++;
        }

        //now search is the node for the last char
        search.word = word;
    }

    public List<String> getAutoCompletes(String prefix) {
        List<String> res = new ArrayList<>();
        char[] arr = prefix.toCharArray();
        Node search = root;
        int i = 0;

        while(i < arr.length) {
            char c = arr[i];
            Node n = search.getNext(c);
            if(n == null) return res;
            search = n;
            i++;
        }

        //now search matches prefix itself
        for(Node n : search.next) {
            if(n == null) continue;
            dfs(n, res);
        }
        return res;
    }

    private void dfs(Node start, List<String> res) {
        if(start.word != null) res.add(start.word);
        for(Node n : start.next) {
            if(n == null) continue;
            dfs(n, res);
        }
    }

    private static class Node {
        String word;
        Node[] next = new Node[26];

        Node getNext(char c) {
            return next[c - 'a'];
        }

        void putNext(Node node, char c) {
            next[c - 'a'] = node;
        }
    }

    public static void main(String[] arg) {
        List<String> dict = Arrays.asList("helloword", "hello", "hellyeah", "hella", "heat", "hi", "hahaha", "oooo");
        AutoComplete ac = new AutoComplete(dict);
        List<String> res = ac.getAutoCompletes("hi");
        for(String w : res) {
            System.out.println(w);
        }
    }

}
