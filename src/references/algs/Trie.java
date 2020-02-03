package references.algs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }

    private TrieNode buildTrie(List<String> words) {
        TrieNode root = new TrieNode();
        for(String word : words) {
            TrieNode node = root;
            for(Character c : word.toCharArray()) {
                if(node.children.containsKey(c)) {
                    node = node.children.get(c);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(c, newNode);
                    node = newNode;
                }
            }
            node.word = word;
        }
        return root;
    }

    private List<String> getPrefixes(TrieNode root, String words){
        TrieNode node = root;
        List<String> prefixes = new ArrayList<>();
        for(Character c : words.toCharArray()) {
            node = node.children.get(c);
            if(node == null) break;
            if(node.word != null) {
                prefixes.add(node.word);
            }
        }
        return prefixes;
    }

}
