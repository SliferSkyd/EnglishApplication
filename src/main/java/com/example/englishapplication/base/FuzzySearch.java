package com.example.englishapplication.base;

import java.util.List;

public class FuzzySearch {
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
    public static String getCorrectWord(String s) {
        List<String> words = DictionaryManagement.getAllWords();
        int minDistance = (int)1e9;
        String correctWord = "";
        for (String word: words) {
            int distance = LevenshteinDistance(s, word);
            if (distance < minDistance) {
                minDistance = distance;
                correctWord = word;
            }
        }
        if (minDistance > 2) return "";
        return correctWord;
    }
}
