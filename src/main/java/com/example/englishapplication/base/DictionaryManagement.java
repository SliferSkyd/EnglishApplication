package com.example.englishapplication.base;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandLine() {
        Scanner getStringInput = new Scanner(System.in);
        Scanner getIntegerInput = new Scanner(System.in);
        int size = getIntegerInput.nextInt();
        for (int i = 0; i < size; ++i) {
            String target = getStringInput.nextLine();
            String meaning = getStringInput.nextLine();
            Dictionary.wordList.add(new Word(target, meaning));
        }
    }

    public static void insertFromFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(IN_PATH);
        Scanner scanner = new Scanner(fileInputStream);
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordInLine = line.split("\t");
                //Dictionary.wordList.add(new Word(wordInLine[0], wordInLine[1]));
                add(wordInLine[0], wordInLine[1]);
            }
        } catch (Exception e) {
            throw new IOException();
        } finally {
            scanner.close();
            fileInputStream.close();
        }
    }

    public static void showAllWords() {
        System.out.printf("%-6s%c %-15s%c %-20s%n","No", '|' ,"English", '|', "Vietnamese");
        Dictionary.trie.lookupWord("");
        /*for (int i = 0; i < Dictionary.wordList.size(); i++) {
            System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1,'|', Dictionary.wordList.get(i).getWordTarget(), '|',Dictionary.wordList.get(i).getWordExplain());
        }*/
    }

    public static void exportToFile() throws IOException {
        File output = new File(OUT_PATH);
        PrintStream stream = new PrintStream(output);
        System.setOut(stream);

        showAllWords();

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        stream.close();
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