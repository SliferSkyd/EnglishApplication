package com.example.englishapplication.controller.game;

import com.example.englishapplication.core.Utils;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class BaseScene extends Utils {
    protected static final int WIDTH = 1000;
    protected static final int HEIGHT = 600;
    protected BorderPane root;

    protected BaseScene(Stage stage) {
        root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/decorate.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/TimeAndSubmit.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/Inform.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/GameOver.css").toExternalForm());
        stage.setScene(scene);
    }
}
