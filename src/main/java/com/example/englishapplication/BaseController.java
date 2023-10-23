package com.example.englishapplication;

import com.example.englishapplication.base.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseController implements Initializable {
    protected static Database favoriteWords;

    protected static Label loadingLabel;

    BaseController() {
        loadingLabel = new Label("Loading...");
        loadingLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red; -fx-font-weight: bold;");
        loadingLabel.setPadding(new javafx.geometry.Insets(10, 10, 0, 0));
    }

    static {
        try {
            favoriteWords = new Database();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void copyToClipboard(String text) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

    public abstract void initialize(URL url, ResourceBundle resourceBundle);
    public abstract void resetAll() throws ClassNotFoundException;
}