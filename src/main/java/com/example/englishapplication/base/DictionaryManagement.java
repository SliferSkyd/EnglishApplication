package com.example.englishapplication.base;

import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline() {
        Scanner getStringInput = new Scanner(System.in);
        Scanner getIntegerInput = new Scanner(System.in);
        int size = getIntegerInput.nextInt();
        for (int i = 0; i < size; ++i) {
            String target = getStringInput.nextLine();
            String meaning = getStringInput.nextLine();
            Word temp = new Word(target, meaning);
            Dictionary.wordList.add(temp);
        }
    }
}
