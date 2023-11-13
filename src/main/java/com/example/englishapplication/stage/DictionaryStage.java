package com.example.englishapplication.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class DictionaryStage extends BaseStage {
    public DictionaryStage() throws IOException {
        super("Dictionary", 200, 5);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/englishapplication/view/container.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
