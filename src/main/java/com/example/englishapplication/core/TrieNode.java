package com.example.englishapplication.core;

public class TrieNode {
    private boolean isEndOfWord;
    public TrieNode[] next;

    public TrieNode() {
        next = new TrieNode[256];
    }

    public TrieNode(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }
}