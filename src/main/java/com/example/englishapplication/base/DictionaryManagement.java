package com.example.englishapplication.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
                Dictionary.wordList.add(new Word(wordInLine[0], wordInLine[1]));
            }
        } finally {
            scanner.close();
            fileInputStream.close();
        }
    }
    static final String IN_PATH = "src/main/resources/WordDictionary/dictionaries.txt";
    public static void add(String target, String explain) {
        Dictionary.wordList.add(new Word(target, explain));
    }
    public static void delete(String target) {
        for (int i = 0; i < Dictionary.wordList.size(); ++i) {
            if (target.equals(Dictionary.wordList.get(i).getWordTarget())) {
                Dictionary.wordList.remove(i);
                return;
            }
        }
    }


    public static void update(String target, String explain) {
        delete(target);
        add(target, explain);
    }

    public static String lookUp(String target) {
        for (int i = 0; i < Dictionary.wordList.size(); ++i) {
            if (target.equals(Dictionary.wordList.get(i).getWordTarget())) {
                return Dictionary.wordList.get(i).getWordExplain();
            }
        }
        return "Error: Word Not Found!";
    }
}