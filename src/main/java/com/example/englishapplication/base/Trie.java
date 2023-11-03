package com.example.englishapplication.base;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;
    public static int index;
    Trie() {
        root = new TrieNode();
    }

    // add duplicated word ????
    public String addWord(String target) {
        TrieNode p = root;
        for (int i = 0; i < target.length(); ++i) {
            int ch = target.charAt(i);
            if (ch >= 256) return "Word: " + target + " is not correct.";

            //if (ch >= 256) System.out.println(target);
            if (p.next[ch] == null) p.next[ch] = new TrieNode();
            p = p.next[ch];
        }

        if (p.isEndOfWord())
            return "Word: " + target + " already exists.";

        p.setEndOfWord(true);
        return "Successfully add word: " + target;
    }

    // delete word or duplicate word ????
    public String deleteWord(String target) {
        TrieNode p = root;
        for (int i = 0; i < target.length(); ++i) {
            int ch = target.charAt(i);
            if (p.next[ch] == null)
                return "Error: Word is not exist";

            p = p.next[ch];
        }

        if (!p.isEndOfWord())
            return "Error: Word is not exist";

        p.setEndOfWord(false);
        return "Successfully delete word: " + target;
    }

    public TrieNode searchWord(String target) {
        TrieNode p = root;
        for (int i = 0; i < target.length(); ++i) {
            int ch = target.charAt(i);
            if (p.next[ch] == null)
                return null;

            p = p.next[ch];
        }

        return p;
    }

    private void dfs(TrieNode u, String prefix, List<String> list) {
        if (u.isEndOfWord()) {
            list.add(prefix);
        }

        for (int i = 0; i < 256; ++i)
            if (u.next[i] != null) dfs(u.next[i], prefix + (char)i, list);
    }

    public List<String> lookupWord(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); ++i) {
            int ch = prefix.charAt(i);
            if (p.next[ch] == null) return new ArrayList<>();
            p = p.next[ch];
        }
        List<String> list = new ArrayList<>();
        index = 0;
        dfs(p, prefix, list);
        return list;
    }
}