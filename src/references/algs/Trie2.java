package references.algs;


import java.util.*;

public class Trie2 {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        List<String> words = new ArrayList<>();
    }

    private TrieNode buildTrie(List<String> words) {
        TrieNode root = new TrieNode();
        for(String word : words) {
            TrieNode node = root;
            for(Character c : word.toCharArray()) {
                if(node.children.containsKey(c)) {
                    //get the node for the current character
                    node = node.children.get(c);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(c, newNode);
                    //create the node for the current character
                    node = newNode;
                }
                //up until this character, forms a prefix for the current word
                node.words.add(word);
            }
        }
        return root;
    }

    private List<String> getPrefixes(TrieNode root, String prefix){
        TrieNode node = root;
        for(Character c : prefix.toCharArray()) {
            if (node != null ) node = node.children.get(c);
            if(node == null) {
                return Collections.emptyList();
            }
        }
        return node.words;
    }

    public static void main(String[] args) {
        Trie2 sol = new Trie2();
        TrieNode dict = sol.buildTrie(Arrays.asList("mobile","mouse","moneypot","monitor","mousepad"));
        List<String> ret = sol.getPrefixes(dict, "mouse");
        for(String s : ret) {
            System.out.println(s);
        }
    }
    
    
}
