package com.example.englishapplication.controller.game;

import com.example.englishapplication.core.Utils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class BaseScene extends Utils {
    protected static final int WIDTH = 1000;
    protected static final int HEIGHT = 600;
    protected BorderPane root;
    protected AnchorPane container;
    protected ImageView correctImage = new ImageView(new Image(getClass().getResourceAsStream("/com/example/englishapplication/image/game/correct.png")));
    protected ImageView incorrectImage = new ImageView(new Image(getClass().getResourceAsStream("/com/example/englishapplication/image/game/wrong.png")));

    protected BaseScene(Stage stage) {
        root = new BorderPane();
        root.setPrefSize(WIDTH, HEIGHT);

        container = new AnchorPane();
        container.setPrefSize(WIDTH, HEIGHT);

        container.getChildren().add(root);
        Scene scene = new Scene(container, WIDTH, HEIGHT);

        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/main.css").toExternalForm());
        stage.setScene(scene);

        correctImage.setFitWidth(256);
        correctImage.setFitHeight(194);
        correctImage.setLayoutX(743);
        correctImage.setLayoutY(411);
        correctImage.setVisible(false);
        container.getChildren().add(correctImage);

        incorrectImage.setFitWidth(291);
        incorrectImage.setFitHeight(202);
        incorrectImage.setLayoutX(360);
        incorrectImage.setLayoutY(400);
        incorrectImage.setVisible(false);
        container.getChildren().add(incorrectImage);


    }
}
