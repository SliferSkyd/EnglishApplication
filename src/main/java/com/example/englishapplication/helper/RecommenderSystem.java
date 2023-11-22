package com.example.englishapplication.helper;

import com.example.englishapplication.core.DictionaryManagement;
import com.example.englishapplication.core.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RecommenderSystem extends Utils {
    private static class Node {
        public String word;
        public int depth;
        public Node(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }
    }
    private static String generate(String s, int maxDepth) {
        Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(new Node(s, 0));
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            String u = curNode.word;
            int depth = curNode.depth;
            if (DictionaryManagement.isExist(u)) return u;
            if (depth == maxDepth) continue;
            for (int i = u.length(); i >= 0; --i) {
                if (i != u.length()) {
                    for (int c = 0; c < 26; ++c) {
                        char ch = (char)('a' + c);
                        queue.add(new Node(u.substring(0, i) + ch + u.substring(i + 1), depth + 1));
                    }
                    queue.add(new Node(u.substring(0, i) + u.substring(i + 1), depth + 1));
                }
                for (int c = 0; c < 26; ++c) {
                    char ch = (char)('a' + c);
                    queue.add(new Node(u.substring(0, i) + ch + u.substring(i), depth + 1));
                }
            }
        }
        return null;
    }
    public static String getCorrectWord(String s) {
        if (s.length() < 3) return null;
        return generate(s, 2);
    }
}
