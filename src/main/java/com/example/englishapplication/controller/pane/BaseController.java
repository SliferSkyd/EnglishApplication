package com.example.englishapplication.controller.pane;

import com.example.englishapplication.core.Utils;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseController extends Utils implements Initializable {
    public abstract void initialize(URL url, ResourceBundle resourceBundle);
    public abstract void resetAll() throws ClassNotFoundException;

    protected static Label loadingLabel;

    public BaseController() {
        loadingLabel = new Label("Loading...");
        loadingLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red; -fx-font-weight: bold;");
        loadingLabel.setPadding(new javafx.geometry.Insets(10, 10, 0, 0));
    }
}