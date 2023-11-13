package com.example.englishapplication;

import com.example.englishapplication.core.DictionaryManagement;
import com.example.englishapplication.core.Database;
import com.example.englishapplication.stage.DictionaryStage;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Dictionary extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        DictionaryManagement.insertFromFile();
        Database.startDatabase();
        new DictionaryStage();
    }

    @Override
    public void stop() throws IOException {
        Database.stopDatabase();
    }
}
