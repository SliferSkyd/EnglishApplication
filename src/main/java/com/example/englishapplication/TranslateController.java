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
                String from = fromLang.getSelectionModel().getSelectedItem();
                String to = toLang.getSelectionModel().getSelectedItem();
                result = TranslateAPI.translate(content, from == "Vietnamese" ? "vi" : "en", to == "Vietnamese" ? "vi" : "en");
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

    public void copyAction() {
        input.setText(output.getEngine().getDocument().getDocumentElement().getTextContent());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fromLang.setItems(FXCollections.observableArrayList(
                "English", "Vietnamese")
        );
        fromLang.getSelectionModel().selectFirst();
        toLang.setItems(FXCollections.observableArrayList(
                "English", "Vietnamese")
        );
        toLang.getSelectionModel().selectLast();
    }
}
