package leetcode;

import java.util.*;

public class Problem1268 {


    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        if(products.length == 0 || searchWord.length() == 0) return Collections.emptyList();

        List<String> prods = Arrays.asList(products);
        TrieNode dict = buildTrie(prods);

        List<List<String>> ret = getPrefixes(dict, searchWord);
//        for(List<String> l : ret) {
//            StringBuilder sb = new StringBuilder();
//            for(String s : l) {
//                sb.append(" ");
//                sb.append(s);
//            }
//            System.out.println(sb);
//        }
        return ret;
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        PriorityQueue<String> words = new PriorityQueue<>((a, b) -> {
            return -a.compareTo(b);
        });
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
                node.words.add(word);
                if(node.words.size() > 3) node.words.poll();
            }
        }
        return root;
    }

    private List<List<String>> getPrefixes(TrieNode root, String words){
        List<List<String>> ret = new ArrayList<>();
        TrieNode node = root;
        for(Character c : words.toCharArray()) {
            if (node != null ) node = node.children.get(c);
            if(node == null) {
                ret.add(Collections.emptyList());
            } else {
                List<String> t = new ArrayList<>(3);
                t.addAll(node.words);
                Collections.sort(t);
                ret.add(t);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Problem1268 sol = new Problem1268();
        sol.suggestedProducts(new String[]{"bags","baggage","banner","box","cloths"}, "bags");
    }
}
