package com.example.englishapplication;

import com.example.englishapplication.base.DictionaryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        DictionaryManagement.insertFromFile();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("container.fxml")));
        primaryStage.setTitle("Dictionary");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() throws IOException {

    }
}
