package com.example.englishapplication.base;

public class Trie {
    protected class Node {
        private String explain;
        private Node[] next;

        public Node() {
            explain = "";
            next = new Node[256];
        }

        public Node(String explain) {
            this.explain = explain;
            next = new Node[256];
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }
    }

    protected Node root;
    protected static int index;
    Trie() {
        root = new Node();
    }

    // add duplicated word ????
    protected String addWord(String target, String explain) {
        Node p = root;
        for (int i = 0; i < target.length(); ++i) {
            int ch = target.charAt(i);
            if (p.next[ch] == null) p.next[ch] = new Node();
            p = p.next[ch];
        }

        if (!p.getExplain().isEmpty())
            return "Word: " + target + " already exists.";

        p.setExplain(explain);
        return "Successfully add word: " + target + " with meaning: " + explain;
    }

    // delete word or duplicate word ????
    protected String deleteWord(String target) {
        Node p = root;
        for (int i = 0; i < target.length(); ++i) {
            int ch = target.charAt(i);
            if (p.next[ch] == null)
                return "Error: Word is not exist";

            p = p.next[ch];
        }

        if (p.getExplain().isEmpty())
            return "Error: Word is not exist";

        p.setExplain("");
        return "Successfully delete word: " + target;
    }

    protected String searchWord(String target) {
        Node p = root;
        for (int i = 0; i < target.length(); ++i) {
            int ch = target.charAt(i);
            if (p.next[ch] == null)
                return "Error: Word is not exist";

            p = p.next[ch];
        }

        if (p.getExplain().isEmpty())
            return "Error: Word is not exist";

        return p.getExplain();
    }

    private void dfs(Node u, String prefix) {
        if (!u.getExplain().isEmpty()) {
            ++index;
            System.out.printf("%-6d%c %-15s%c %-15s%n", index, '|', prefix, '|', u.getExplain());
        }

        for (int i = 0; i < 256; ++i)
            if (u.next[i] != null) dfs(u.next[i], prefix + (char)i);
    }

    public void printToRootFile(Node u, String prefix) {
        if (!u.getExplain().isEmpty()) {
            System.out.println(prefix + "\t" + u.getExplain());
        }

        for (int i = 0; i < 256; ++i)
            if (u.next[i] != null) printToRootFile(u.next[i], prefix + (char)i);
    }

    protected void lookupWord(String prefix) {
        Node p = root;
        for (int i = 0; i < prefix.length(); ++i) {
            int ch = prefix.charAt(i);
            if (p.next[ch] == null) return;
            p = p.next[ch];
        }

        index = 0;
        dfs(p, prefix);
    }
}