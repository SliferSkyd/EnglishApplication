package com.example.englishapplication.Game;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScene extends Application {
    private GameManager gameManager;
    private UIManager uiManager;

    public void start(Stage stage) throws IOException {
        gameManager = new GameManager();
        gameManager.initializeGame();

        TimeController timeController = new TimeController("01:00",60);

        uiManager = new UIManager(stage, gameManager,timeController);
        timeController.startTimer(uiManager);

        uiManager.initializeUI(stage);
        stage.setOnCloseRequest(event -> {
            gameManager.soundManager.stopSoundEffect();
        });
    }

    public static void main(String[] args) throws IOException {
        //DictionaryManagement.insertFromFile();`
        launch(args);
    }
}