package com.example.englishapplication.stage;

import com.example.englishapplication.controller.element.PopUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class PopUp extends BaseStage {
    public PopUp(String word) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/englishapplication/view/popup.fxml"));
            AnchorPane root = loader.load();
            PopUpController controller = loader.getController();
            controller.setWord(word);

            Stage stage = newStage("Add new word");

            Scene scene = new Scene(root);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/com/example/englishapplication/style/popup.css")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
