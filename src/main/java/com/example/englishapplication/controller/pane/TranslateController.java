package com.example.englishapplication.controller.pane;

import com.example.englishapplication.helper.TranslateAPI;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.englishapplication.helper.VoiceAPI.getTextToSpeech;

public class TranslateController extends BaseController implements Initializable {
    public TextArea input;
    public WebView output;

    public ChoiceBox<String> fromLang;
    public ChoiceBox<String> toLang;
    public void translateAction() {
        String content = input.getText();
        parallelProcessing(() -> {
            String result = "";
            try {
                String from = fromLang.getSelectionModel().getSelectedItem();
                String to = toLang.getSelectionModel().getSelectedItem();
                result = TranslateAPI.translate(content, Objects.equals(from, "Vietnamese") ? "vi" : "en", Objects.equals(to, "Vietnamese") ? "vi" : "en");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String finalResult = result;
            Platform.runLater(() -> output.getEngine().loadContent(finalResult));
        });

        output.getEngine().loadContent("Translating...");
    }

    public void copyInputToClipboardAction() {
        copyToClipboard(input.getText());
    }

    public void copyOutputToClipboardAction() {
        copyToClipboard(output.getEngine().getDocument().getDocumentElement().getTextContent());
    }

    public void pronounceInputAction() {
        getTextToSpeech(input.getText(), Objects.equals(fromLang.getSelectionModel().getSelectedItem(), "Vietnamese") ? "vi" : "en");
    }

    public void pronounceOutputAction() {
        getTextToSpeech(output.getEngine().getDocument().getDocumentElement().getTextContent(), Objects.equals(toLang.getSelectionModel().getSelectedItem(), "Vietnamese") ? "vi" : "en");
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
