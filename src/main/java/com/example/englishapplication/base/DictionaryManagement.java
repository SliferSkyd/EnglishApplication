package com.example.englishapplication.base;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(IN_PATH);
        Scanner scanner = new Scanner(fileInputStream);
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordInLine = line.split("\t");
                add(wordInLine[0]);
            }
        } catch (Exception e) {
            throw new IOException();
        } finally {
            scanner.close();
            fileInputStream.close();
        }
    }

    public static List<String> getAllWords() {
        return Dictionary.trie.lookupWord("");
    }

    public static void exportToFile() throws IOException {

    }

    static final String IN_PATH = "src/main/resources/WordDictionary/dictionaries.txt";
    static final String OUT_PATH = "src/main/resources/WordDictionary/data.txt";

    public static String add(String target) {
        return Dictionary.trie.addWord(target);
    }

    public static String delete(String target) {
        return Dictionary.trie.deleteWord(target);
    }

    public static String update(String target, String explain) {
        String temp = delete(target);
        if (Objects.equals(temp, "Error: Word is not exist")) return temp;
        return add(target);
    }

    public static JSONObject Search(String target) {
        try {
            return Dictionary.dictionary.getJSONObject(target);
        }
        catch (Exception e) {
            System.out.println("Can't find word " + target);
            e.getStackTrace();
            return null;
        }
    }

    public static List<String> LookUp(String prefix) {
        return Dictionary.trie.lookupWord(prefix);
    }

    public static void main(String[] args) {
        System.out.println(DictionaryManagement.Search("binary"));
        //DictionaryManagement.init();
        JSONObject list = DictionaryManagement.Search("binary");
        JSONArray list2 = list.getJSONArray("type");

        for (Object object : list2) {
            if (object instanceof JSONObject) {
                for (Object key : ((JSONObject) object).keySet()) {
                    System.out.println(key);
                    System.out.println(((JSONObject) object).get(key.toString()));
                }
            }
        }
    }
}
