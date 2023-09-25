package com.example.englishapplication.base;

import java.io.IOException;

public class DictionaryCommandLine {
    public static void showAllWords() {
        System.out.printf("%-6s%c %-15s%c %-20s%n","No", '|' ,"English", '|', "Vietnamese");
        for (int i = 0; i < Dictionary.wordList.size(); i++) {
            System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1,'|', Dictionary.wordList.get(i).getWordTarget(), '|',Dictionary.wordList.get(i).getWordExplain());
        }
    }
    public static void dictionaryBasic() {
        try {
            DictionaryManagement.insertFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        showAllWords();
    }
    public static void main(String[] args) {
        dictionaryBasic();
    }
}
