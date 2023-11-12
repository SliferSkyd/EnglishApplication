package com.example.englishapplication.stage;

import com.example.englishapplication.core.Utils;
import javafx.application.Platform;
import javafx.stage.Stage;

public class BaseStage extends Utils {
    protected boolean isClosed = false;
    protected Stage newStage(String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            if (confirm("Are you sure you want to close this window?", "You will lose all your data")) {
                stage.close();
                isClosed = true;
            } else {
                e.consume();
            }
        });
        return stage;
    }
}
