package com.example.englishapplication.controller.game;

import com.example.englishapplication.core.Utils;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.WHITE;

public class GameOver extends Utils {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    private BorderPane root = new BorderPane();
    private JFXButton playAgainButton = new JFXButton("Replay");
    public GameOver(Stage stage, int score, String time) {
        StackPane content = new StackPane();
        ImageView gameOverImage = new ImageView(new Image(getClass().getResourceAsStream("/com/example/englishapplication/image/game/GameOver.png")));
        gameOverImage.setFitWidth(WIDTH / 1.85);
        gameOverImage.setFitHeight(HEIGHT / 1.5);
        Text gameOverText = new Text("Congratulations!");
        gameOverText.getStyleClass().add("GameOver");
        gameOverText.getStyleClass().add("Congratulations"); // Đặt lớp CSS cho văn bản nếu cần
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

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/decorate.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/TimeAndSubmit.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/Inform.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/GameOver.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        playAgainButton.setOnMouseClicked(event -> {
            stage.fireEvent(new GameEvent(GameEvent.PLAY_AGAIN, 0, ""));
        });


    }
}
