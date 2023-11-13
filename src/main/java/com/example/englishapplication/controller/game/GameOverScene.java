package com.example.englishapplication.controller.game;

import animatefx.animation.*;
import com.example.englishapplication.helper.MediaManager;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.WHITE;

public class GameOverScene extends BaseScene {
    private JFXButton playAgainButton = new JFXButton("Replay");
    public GameOverScene(Stage stage, int score, String time) {
        super(stage);
        StackPane content = new StackPane();
        ImageView gameOverImage = new ImageView(new Image(getClass().getResourceAsStream("/com/example/englishapplication/image/game/GameOver.png")));
        gameOverImage.setFitWidth(WIDTH / 1.85);
        gameOverImage.setFitHeight(HEIGHT / 1.5);
        Text gameOverText = new Text("Congratulations!");
        gameOverText.getStyleClass().add("GameOver");
        gameOverText.getStyleClass().add("Congratulations");
        gameOverText.setTranslateY(60);
        VBox gameOverBox = new VBox();
        gameOverBox.setSpacing(20);

        playAgainButton.getStyleClass().add("submit-button");

        HBox bottomBox = new HBox();
        bottomBox.setPadding(new Insets(0, 23, 12, 0));
        bottomBox.setSpacing(690);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.getChildren().addAll(new HBox(playAgainButton));
        root.setBottom(bottomBox);

        Text FinalScore = new Text("Score: " + score + " points");
        FinalScore.getStyleClass().add("GameOver");
        FinalScore.setTranslateX(40);
        FinalScore.setTranslateY(65);

        Text timeText = new Text(time);
        timeText.getStyleClass().add("time");
        timeText.setFill(WHITE);
        timeText.setTranslateX(70);
        timeText.setTranslateY(70);

        gameOverBox.getChildren().addAll(gameOverText, FinalScore, timeText);
        gameOverBox.setPadding(new Insets(160, 0, -450, 390));
        content.getChildren().addAll(gameOverImage, gameOverBox);
        root.setCenter(content);

        playAgainButton.setOnMouseClicked(event -> {
            stage.fireEvent(new GameEvent(GameEvent.PLAY_AGAIN, 0, ""));
        });
        MediaManager.playBackgroundMusic(MediaManager.Sound.GAME_OVER, 0.25);
        new JackInTheBox(root).play();
    }
}
