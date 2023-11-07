package com.example.englishapplication.core;

public class Word {
    private String target, explain;

    public Word(String target, String explain) {
        this.explain = explain;
        this.target = target;
    }

    public String getExplain() {
        return explain;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
