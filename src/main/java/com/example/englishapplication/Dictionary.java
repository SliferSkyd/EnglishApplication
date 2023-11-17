package com.example.englishapplication;

import com.example.englishapplication.core.Database;
import com.example.englishapplication.core.DictionaryManagement;
import com.example.englishapplication.stage.DictionaryStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Dictionary extends Application {

    public static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        DictionaryManagement.insertFromFile();
        Database.startDatabase();
        new DictionaryStage();
    }

    @Override
    public void stop() {
        Database.stopDatabase();
        DictionaryManagement.saveData();
    }
}
