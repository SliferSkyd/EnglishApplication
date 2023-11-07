package com.example.englishapplication.controller;

import com.example.englishapplication.helper.Database;
import com.example.englishapplication.base.Utils;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseController extends Utils implements Initializable {
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


    public abstract void initialize(URL url, ResourceBundle resourceBundle);
    public abstract void resetAll() throws ClassNotFoundException;
}