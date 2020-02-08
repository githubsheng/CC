package leetcode;

import java.util.*;

//20:36 - 21:10
public class Problem140 {

    private List<String> ret = new ArrayList<>();
    private TrieNode root;

    public List<String> wordBreak(String s, List<String> wordDict) {
        if(!canBreak(s, wordDict)) return Collections.emptyList();
        if(s.length() == 0) return Collections.emptyList();
        if(wordDict.isEmpty()) return Collections.emptyList();
        buildTrie(wordDict);
        helper(s, "");
        return ret;
    }

    private void helper(String s, String subRet) {
        List<String> prefixes = getPrefixes(s);
        for(String p : prefixes) {
            String r = subRet.length() == 0 ? p : subRet + " " + p;
            int size = p.length();
            if(size == s.length()) {
                ret.add(r);
            }
            String substring = s.substring(size);
            helper(substring, r);
        }
    }

    private List<String> getPrefixes(String s) {
        TrieNode node = root;
        List<String> prefixes = new ArrayList<>();
        for(Character c : s.toCharArray()) {
            node = node.children.get(c);
            if(node == null) break;
            if(node.word != null) {
                prefixes.add(node.word);
            }
        }
        return prefixes;
    }


    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }

    private void buildTrie(List<String> words){
        root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }
    }

    private boolean canBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        Problem140 sol = new Problem140();
        List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> ret = sol.wordBreak("catsanddog", dict);
        for (String s : ret) {
            System.out.println(s);
        }
    }


}