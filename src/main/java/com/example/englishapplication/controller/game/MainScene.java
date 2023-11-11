package com.example.englishapplication.controller.game;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScene extends Application {
    public void start(Stage stage) throws IOException {
        UIManager uiManager = new UIManager(stage);

        stage.addEventFilter(GameEvent.GAME_OVER, event -> {
            stage.close();
        });
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}