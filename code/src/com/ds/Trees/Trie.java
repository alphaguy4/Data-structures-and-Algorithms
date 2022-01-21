package com.ds.Trees;

import java.util.Map;
import java.util.HashMap;

public class Trie {
    static class TrieNode {
        Map<Character, TrieNode> children =
                new HashMap<Character, TrieNode>();
        String word = "";
    }

    static class SuffixTrie {
        TrieNode root;
        char endSymbol = '*';

        public SuffixTrie(String[] words) {
            root = new TrieNode();
            for(String word: words) {
                populateTrieWith(root, word);
            }
        }

        private void populateTrieWith(TrieNode root, String word) {
            TrieNode node = root;
            for(int idx = 0; idx < word.length(); idx++) {
                char letter = word.charAt(idx);
                if(!node.children.containsKey(letter)) {
                    node.children.put(letter, new TrieNode());
                }
                node = node.children.get(letter);
            }

            node.children.put(endSymbol, new TrieNode());
            node.word = word;
        }
    }
}
