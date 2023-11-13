package com.example.englishapplication.stage;

import com.example.englishapplication.controller.element.PopUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PopUpStage extends BaseStage {
    public PopUpStage(String word) {
        super("Add new word", 400, 60);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/englishapplication/view/popup.fxml"));
            AnchorPane root = loader.load();
            PopUpController controller = loader.getController();
            controller.setWord(word);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/com/example/englishapplication/style/popup.css")));
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
