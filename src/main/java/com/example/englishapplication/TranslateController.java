package com.example.englishapplication;

import com.example.englishapplication.base.TranslateAPI;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    public TextArea input;
    public WebView output;

    public ChoiceBox<String> fromLang;
    public ChoiceBox<String> toLang;
    public void translateAction() {
        String content = input.getText();
        Thread thread = new Thread(() -> {
            String result = "";
            try {
                result = TranslateAPI.translate(content, "en", "vi");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String finalResult = result;
            Platform.runLater(() -> output.getEngine().loadContent(finalResult));
        });
        thread.setDaemon(true);
        thread.start();

        output.getEngine().loadContent("Translating...");
    }
    public void inputAction() throws Exception {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fromLang.setItems(FXCollections.observableArrayList(
                "English", "Vietnamese")
        );
    }
}
