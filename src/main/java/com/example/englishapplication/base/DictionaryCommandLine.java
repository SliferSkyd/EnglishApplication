package com.example.englishapplication.base;

import java.util.Scanner;

public class DictionaryCommandLine {
    public static void showAllWords() {
        System.out.printf("%-6s%c %-15s%c %-20s%n","No", '|' ,"English", '|', "Vietnamese");
        for (int i = 0; i < Dictionary.wordList.size(); i++) {
            System.out.printf("%-6d%c %-15s%c %-15s%n", i + 1,'|', Dictionary.wordList.get(i).getWordTarget(), '|',Dictionary.wordList.get(i).getWordExplain());
        }
    }
    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandLine();
        showAllWords();
    }
    public static void dictionaryAdvanced() {
        while (true) {
            String menu = "Welcome to My Application\n" +
                    "[0] Exit\n" +
                    "[1] Add\n" +
                    "[2] Remove\n" +
                    "[3] Update\n" +
                    "[4] Display\n" +
                    "[5] Lookup\n" +
                    "[6] Search\n" +
                    "[7] Game\n" +
                    "[8] Import from file\n" +
                    "[9] Export to file\n" +
                    "Your action: ";
            System.out.print(menu);
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:

            }
        }
    }
    public static void main(String[] args) {
        dictionaryAdvanced();
    }
}
