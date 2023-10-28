package com.example.englishapplication.base;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(IN_PATH);
        Scanner scanner = new Scanner(fileInputStream);
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordInLine = line.split("\t");
                add(wordInLine[0], wordInLine[1]);
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
    public static String add(String target, String explain) {
        return Dictionary.trie.addWord(target, explain);
    }
    public static String delete(String target) {
        return Dictionary.trie.deleteWord(target);
    }

    public static String update(String target, String explain) {
        String temp = delete(target);
        if (temp == "Error: Word is not exist") return temp;
        return add(target, explain);
    }

    public static String Search(String target) {
        return Dictionary.trie.searchWord(target);
    }

    public static List<String> LookUp(String prefix) {
        return Dictionary.trie.lookupWord(prefix);
    }
}