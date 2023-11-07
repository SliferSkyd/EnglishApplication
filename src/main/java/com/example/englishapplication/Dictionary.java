package com.example.englishapplication;

import com.example.englishapplication.base.DictionaryManagement;
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/container.fxml")));
        primaryStage.setTitle("Dictionary");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root);
/*
        scene.getStylesheets().add(String.valueOf(getClass().getResource("style/container.css")));
        scene.getStylesheets().add(String.valueOf(getClass().getResource("style/search.css")));
        scene.getStylesheets().add(String.valueOf(getClass().getResource("style/translate.css")));
        scene.getStylesheets().add(String.valueOf(getClass().getResource("style/relatives.css")));
        scene.getStylesheets().add(String.valueOf(getClass().getResource("style/favorite.css")));
        scene.getStylesheets().add(String.valueOf(getClass().getResource("style/popup.css")));
*/
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws IOException {
    }
}
