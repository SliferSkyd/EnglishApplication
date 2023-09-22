package com.example.englishapplication.base;

public class Word {
    private String wordTarget, wordExplain;
    Word(String target, String explain) {
        wordExplain = explain;
        wordTarget = target;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public String getWordTarget() {
        return wordTarget;
    }
}
