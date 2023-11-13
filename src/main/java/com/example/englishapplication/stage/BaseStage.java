package com.example.englishapplication.stage;

import com.example.englishapplication.core.Utils;
import javafx.stage.Stage;

public class BaseStage extends Utils {
    protected Stage stage;
    protected BaseStage(String title, int x, int y) {
        stage = new Stage();
        stage.setX(x);
        stage.setY(y);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            if (confirm("Are you sure you want to close this window?", "You will lose all your data")) {
                stage.close();
            } else {
                e.consume();
            }
        });
        stage.show();
    }
}
