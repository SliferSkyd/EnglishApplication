package com.example.englishapplication.base;

import java.io.*;
import java.util.Objects;
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

    public static void insertFromFile(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        Scanner scanner = new Scanner(fileInputStream);
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordInLine = line.split("\t");
                //Dictionary.wordList.add(new Word(wordInLine[0], wordInLine[1]));

                System.out.println(line);

                StringBuilder meaning = new StringBuilder();
                for (int i = 1; i < wordInLine.length; ++i) {
                    meaning.append(wordInLine[i]);
                    if (i != wordInLine.length - 1) meaning.append("\t");
                };

                Dictionary.trie.addWord(wordInLine[0], meaning.toString());
            }
        } catch (Exception e) {
            throw new IOException();
        } finally {
            scanner.close();
            fileInputStream.close();
        }
    }

    public static void showAllWords() {
        System.out.printf("%-6s%c %-50s%c %-20s%n","No", '|' ,"English", '|', "Vietnamese");
        Dictionary.trie.lookupWord("");
        /*for (int i = 0; i < Dictionary.wordList.size(); i++) {
            System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1,'|', Dictionary.wordList.get(i).getWordTarget(), '|',Dictionary.wordList.get(i).getWordExplain());
        }*/
    }

    public static void exportToFile(String path) throws IOException {
        File output = new File(path);
        PrintStream stream = new PrintStream(output);
        System.setOut(stream);

        if (path.equals(IN_PATH)) {
            Dictionary.trie.printToRootFile(Dictionary.trie.root, "");
        } else {
            showAllWords();
        }

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        stream.close();
    }


    static final String IN_PATH = "src/main/resources/com/example/englishapplication/data/CommandLineData.txt";
    static final String OUT_PATH = "src/main/resources/com/example/englishapplication/data/data.txt";
    public static String add(String target, String explain) {
        //Dictionary.wordList.add(new Word(target, explain));
        return Dictionary.trie.addWord(target, explain);
        //return "Successfully add word: " + target + " with meaning: " + explain;
    }
    public static String delete(String target) {
        /*for (int i = 0; i < Dictionary.wordList.size(); ++i) {
            if (target.equals(Dictionary.wordList.get(i).getWordTarget())) {
                Dictionary.wordList.remove(i);
                return "Successfully delete word: " + target;
            }
        }

        return "Error: Word is not exist";*/
        return Dictionary.trie.deleteWord(target);
    }

    public static String update(String target, String explain) {
        String temp = delete(target);
        if (Objects.equals(temp, "Error: Word is not exist")) return temp;

        return add(target, explain);
    }

    public static String Search(String target) {
        /*for (int i = 0; i < Dictionary.wordList.size(); ++i) {
            if (target.equals(Dictionary.wordList.get(i).getWordTarget())) {
                return Dictionary.wordList.get(i).getWordExplain();
            }
        }*/

        return Dictionary.trie.searchWord(target);
    }

    public static void LookUp(String prefix) {
        /*List<Word> wordList = new ArrayList<>();
        for (int i = 0; i < Dictionary.wordList.size(); ++i) {
            if (Dictionary.wordList.get(i).getWordTarget().length() >= target.length() && target.equals(Dictionary.wordList.get(i).getWordTarget().substring(0, target.length()))) {
                wordList.add(Dictionary.wordList.get(i));
            }
        }*/
        System.out.printf("%-6s%c %-50s%c %-20s%n", "No", '|' ,"English", '|', "Vietnamese");
        Dictionary.trie.lookupWord(prefix);
        /*for (int i = 0; i < wordList.size(); i++) {
            System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1,'|', wordList.get(i).getWordTarget(), '|', wordList.get(i).getWordExplain());
        }*/
    }
}