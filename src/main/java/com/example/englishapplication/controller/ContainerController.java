package com.example.englishapplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {
    private SearchController searchController;
    private TranslateController translateController;
    private RelativesController relativesController;
    private FavoriteController favoriteController;

    @FXML
    private AnchorPane mainContent, searchPane, translatePane, relativesPane, favoritePane;

    @FXML
    private Button searchButton, translateButton, relativesButton, favoriteButton;

    public void setActive(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }

    public void resetAll() {
        searchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
        relativesButton.getStyleClass().removeAll("active");
        favoriteButton.getStyleClass().removeAll("active");
    }

    @FXML
    public void activeSearchPane() {
        resetAll();
        searchButton.getStyleClass().add("active");
        setActive(searchPane);
        searchController.resetAll();
    }

    @FXML
    public void activeTranslatePane() {
        resetAll();
        translateButton.getStyleClass().add("active");
        setActive(translatePane);
        translateController.resetAll();
    }

    public void activeRelativesPane() {
        resetAll();
        relativesButton.getStyleClass().add("active");
        setActive(relativesPane);
        relativesController.resetAll();
    }

    public void activeFavoritePane() throws ClassNotFoundException {
        resetAll();
        favoriteButton.getStyleClass().add("active");
        setActive(favoritePane);
        favoriteController.resetAll();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/englishapplication/view/search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/englishapplication/view/translate.fxml"));
            translatePane = loader.load();
            translateController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/englishapplication/view/relatives.fxml"));
            relativesPane = loader.load();
            relativesController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/englishapplication/view/favorite.fxml"));
            favoritePane = loader.load();
            favoriteController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        activeSearchPane();
    }
}
