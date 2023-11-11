package com.example.englishapplication.controller.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generate {
    private final static String filePath = "src/main/resources/com/example/englishapplication/data/CommonWords.txt";
    private static List<String> words = new ArrayList<>();
    public static void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(words);
    }
    static {
        loadData();
    }
    private static int currentIndex = 0;
    public static String getRandomWord() {
        String ret = words.get(currentIndex);
        ++currentIndex;
        if (currentIndex == words.size()) {
            currentIndex = 0;
            Collections.shuffle(words);
        }
        return ret;
    }
}
