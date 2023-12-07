package com.example.englishapplication.base;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandLine {
    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandLine();
        DictionaryManagement.showAllWords();
    }
    public static void dictionaryAdvanced() throws IOException {
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
                case 0: {
                    DictionaryManagement.exportToFile(DictionaryManagement.IN_PATH);
                    System.exit(0);
                }
                case 1: {
                    System.out.println("Type your word and meaning in two separated lines:");
                    Scanner lineScanner = new Scanner(System.in);
                    String target = lineScanner.nextLine();
                    String explain = lineScanner.nextLine();

                    target = target.toLowerCase();
                    System.out.println(DictionaryManagement.add(target, explain));
                    break;
                }
                case 2: {
                    System.out.print("Type your word: ");
                    Scanner lineScanner = new Scanner(System.in);
                    String target = lineScanner.nextLine();

                    target = target.toLowerCase();
                    System.out.println(DictionaryManagement.delete(target));
                    break;
                }
                case 3: {
                    System.out.println("Type your word and meaning in two separated lines:");
                    Scanner lineScanner = new Scanner(System.in);
                    String target = lineScanner.nextLine();
                    String explain = lineScanner.nextLine();

                    target = target.toLowerCase();
                    System.out.println(DictionaryManagement.update(target, explain));
                    break;
                }
                case 4: {
                    DictionaryManagement.showAllWords();
                    break;
                }
                case 5: {
                    System.out.print("Type your word: ");
                    Scanner lineScanner = new Scanner(System.in);
                    String target = lineScanner.nextLine();

                    target = target.toLowerCase();
                    DictionaryManagement.LookUp(target);
                    break;
                }
                case 6: {
                    System.out.print("Type your word: ");
                    Scanner lineScanner = new Scanner(System.in);
                    String target = lineScanner.nextLine();

                    target = target.toLowerCase();
                    System.out.println(DictionaryManagement.Search(target));
                    break;
                }
                case 7: {
                    break;
                }
                case 8: {
                    DictionaryManagement.insertFromFile(DictionaryManagement.IN_PATH);
                    break;
                }
                case 9: {
                    DictionaryManagement.exportToFile(DictionaryManagement.OUT_PATH);
                    break;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        dictionaryAdvanced();
    }
}