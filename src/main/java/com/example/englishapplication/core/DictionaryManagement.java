package com.example.englishapplication.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DictionaryManagement extends Utils {
    static final String RESOURCES = "src/main/resources/com/example/englishapplication/data/EngToVie.json";
    public static Trie trie = new Trie();
    public static JSONObject dictionary;

    public static void insertFromFile() {
        parallelProcessing(() -> {
            try {
                String content = new String(Files.readAllBytes(Path.of(RESOURCES)));
                dictionary = new JSONObject(content);
                dictionary.keys().forEachRemaining(trie::addWord);
            } catch (IOException e) {
                System.out.println("Can't load file");
            }
        });
    }

    public static void saveData() {
        //parallelProcessing(() -> {
            try {
                Files.writeString(Path.of(RESOURCES), dictionary.toString());
            } catch (IOException e) {
                System.out.println("Can't save file");
            }
        //});
    }
    private static void buildTree(JSONArray array, PrintWriter writer, int depth) {
        for (int i = 0; i < array.length(); ++i) {
            JSONObject object = array.getJSONObject(i);
            String name = object.keys().next();
            if (object.get(name) instanceof JSONArray) {
                writer.println("\t".repeat(depth) + name);
                buildTree(object.getJSONArray(name), writer, depth + 1);
            } else {
                writer.println("\t".repeat(depth) + name + ": " + object.getString(name));
            }
        }
    }
    public static void exportToFile(File file) throws FileNotFoundException {
        parallelProcessing(() -> {
            PrintWriter writer = new PrintWriter(file);
            List<String> words = LookUp("");
            words.forEach((word) -> {
                String phonetic = dictionary.getJSONObject(word).getString("pronoun");
                writer.println("@" + word + " [" + phonetic + "]");
                JSONObject meaning = dictionary.getJSONObject(word);
                buildTree(meaning.getJSONArray("type"), writer, 0);
                writer.println();
            });
            writer.close();
        });
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


    public static boolean add(String target, JSONObject meaning) {
        if (trie.addWord(target)) {
            dictionary.put(target, meaning);
            return true;
        } else {
            return false;
        }
    }

    public static boolean delete(String target) {
        if (trie.deleteWord(target)) {
            dictionary.remove(target);
            return true;
        } else {
            return false;
        }
    }

    public static boolean update(String target, JSONObject meaning) {
        if (!delete(target)) return false;
        dictionary.remove(target);

        if (add(target, meaning)) {
            return true;
        } else {
            add(target, meaning);
            return false;
        }
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
