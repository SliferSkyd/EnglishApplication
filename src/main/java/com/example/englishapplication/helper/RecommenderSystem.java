package com.example.englishapplication.helper;

import com.example.englishapplication.base.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class RecommenderSystem {
    private static int LevenshteinDistance(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                dp[i][j] = (int)1e9;
            }
        }
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i > 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                if (i > 0 && j > 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                if (i > 0 && j > 0 && a.charAt(i - 1) == b.charAt(j - 1)) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
            }
        }
        return dp[n][m];
    }

    private static void generate(List<String> words, String s, int depth, int maxDepth) {
        if (words.size() > 0) return;
        if (depth == maxDepth) {
            if (Dictionary.trie.containsWord(s)) {
                words.add(s);
            }
            return;
        }
        for (int i = s.length(); i >= 0; --i) {
            if (i != s.length()) {
                for (int c = 0; c < 26; ++c) {
                    char ch = (char)('a' + c);
                    generate(words, s.substring(0, i) + ch + s.substring(i + 1), depth + 1, maxDepth);
                }
                generate(words, s.substring(0, i) + s.substring(i + 1), depth + 1, maxDepth);
            }
            for (int c = 0; c < 26; ++c) {
                char ch = (char)('a' + c);
                generate(words, s.substring(0, i) + ch + s.substring(i), depth + 1, maxDepth);
            }
        }

    }
    public static String getCorrectWord(String s) {
        if (s.length() < 3) return null;
        List<String> words = new ArrayList<>();
        generate(words, s, 0, 2);
        if (words.size() > 0) return words.get(0);
        else return null;
    }
}
