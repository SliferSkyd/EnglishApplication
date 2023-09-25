package com.example.englishapplication.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline() {
        Scanner getStringInput = new Scanner(System.in);
        Scanner getIntegerInput = new Scanner(System.in);
        int size = getIntegerInput.nextInt();
        for (int i = 0; i < size; ++i) {
            String target = getStringInput.nextLine();
            String meaning = getStringInput.nextLine();
            Dictionary.wordList.add(new Word(target, meaning));
        }
    }

    static final String IN_PATH = "src/main/resources/WordDictionary/dictionaries.txt";
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
}