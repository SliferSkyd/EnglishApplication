package com.example.englishapplication.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class DictionaryStage extends BaseStage {
    public DictionaryStage() throws IOException {
        super("Dictionary", 200, 5);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/englishapplication/view/container.fxml")));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResource("/com/example/englishapplication/image/favicon.png").toString()));
        stage.setScene(scene);
    }
}
