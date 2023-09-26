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
                Dictionary.wordList.add(new Word(wordInLine[0], wordInLine[1]));
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
        for (int i = 0; i < Dictionary.wordList.size(); i++) {
            System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1,'|', Dictionary.wordList.get(i).getWordTarget(), '|',Dictionary.wordList.get(i).getWordExplain());
        }
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

    public static String Search(String target) {
        for (int i = 0; i < Dictionary.wordList.size(); ++i) {
            if (target.equals(Dictionary.wordList.get(i).getWordTarget())) {
                return Dictionary.wordList.get(i).getWordExplain();
            }
        }
        return "Error: Word Not Found!";
    }

    public static void LookUp(String target) {
        List<Word> wordList = new ArrayList<>();
        for (int i = 0; i < Dictionary.wordList.size(); ++i) {
            if (Dictionary.wordList.get(i).getWordTarget().length() >= target.length() && target.equals(Dictionary.wordList.get(i).getWordTarget().substring(0, target.length()))) {
                wordList.add(Dictionary.wordList.get(i));
            }
        }
        System.out.printf("%-6s%c %-15s%c %-20s%n", "No", '|' ,"English", '|', "Vietnamese");
        for (int i = 0; i < wordList.size(); i++) {
            System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1,'|', wordList.get(i).getWordTarget(), '|', wordList.get(i).getWordExplain());
        }
    }
}