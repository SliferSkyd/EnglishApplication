package com.example.englishapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class ContainerController implements Initializable {
    SearchController searchController;
    TranslateController translateController;
    @FXML
    private AnchorPane mainContent, searchPane, translatePane;
    @FXML
    private Button searchButton, translateButton;
    public void setActive(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }

    public void resetAll() {
        searchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
    }

    @FXML
    public void activeSearchPane() {
        resetAll();
        searchButton.getStyleClass().add("active");
        setActive(searchPane);
    }

    @FXML
    public void activeTranslatePane() {
        resetAll();
        translateButton.getStyleClass().add("active");
        setActive(translatePane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("translate.fxml"));
            translatePane = loader.load();
            translateController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        activeSearchPane();
    }
}
