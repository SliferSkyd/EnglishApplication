package com.example.englishapplication.base;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    public static List<Word> wordList = new ArrayList<>();

    public static Trie trie = new Trie();

    public static JSONObject dictionary;

    static {
        try {
            String content = new String(Files.readAllBytes(Path.of("src/main/resources/WordDictionary/EngToVie.json")));
            Dictionary.dictionary = new JSONObject(content);
            Dictionary.dictionary.keys().forEachRemaining(Dictionary.trie::addWord);
        } catch (IOException e) {
            System.out.println("Can't load file");
        }
    }
}
