package com.example.englishapplication.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DictionaryManagement {
    public static Trie trie = new Trie();

    public static JSONObject dictionary;
    public static void insertFromFile() {
        try {
            String content = new String(Files.readAllBytes(Path.of(IN_PATH)));
            dictionary = new JSONObject(content);
            dictionary.keys().forEachRemaining(trie::addWord);
        } catch (IOException e) {
            System.out.println("Can't load file");
        }
    }


    public static void exportToFile() {

    }

    public static String getMeaning(String word) {
        JSONObject object = search(word);

        JSONArray array = (JSONArray) object.get("type");
        if (array.length() == 0) {
            throw new RuntimeException("Not Found");
        }
        object = array.getJSONObject(0);
        String name = object.keys().next();
        array = (JSONArray) object.get(name);
        if (array.length() == 0) {
            throw new RuntimeException("Not Found");
        }
        return array.getJSONObject(0).keys().next();
    }

    static final String IN_PATH = "src/main/resources/com/example/englishapplication/data/EngToVie.json";
    static final String OUT_PATH = "src/main/resources/com/example/englishapplication/data/data.json";

    public static boolean add(String target, JSONObject meaning) {
        return trie.addWord(target);
    }

    public static boolean delete(String target) {
        return trie.deleteWord(target);
    }

    public static boolean update(String target, JSONObject meaning) {
        if (!delete(target)) return false;
        return add(target, meaning);
    }

    public static JSONObject search(String target) {
        try {
            return dictionary.getJSONObject(target);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public static boolean isExist(String target) {
        return trie.containsWord(target);
    }

    public static List<String> LookUp(String prefix) {
        return trie.lookupWord(prefix);
    }
}
