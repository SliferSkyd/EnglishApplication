package com.example.englishapplication.controller.pane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {
    private BaseController searchController, translateController, relativesController, favoriteController, gameController;

    @FXML
    private AnchorPane mainContent, searchPane, translatePane, relativesPane, favoritePane, gamePane;

    @FXML
    private Button searchButton, translateButton, relativesButton, favoriteButton, gameButton;

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
    public void activeSearchPane() throws ClassNotFoundException {
        resetAll();
        searchButton.getStyleClass().add("active");
        setActive(searchPane);
        searchController.resetAll();
    }

    @FXML
    public void activeTranslatePane() throws ClassNotFoundException {
        resetAll();
        translateButton.getStyleClass().add("active");
        setActive(translatePane);
        translateController.resetAll();
    }

    public void activeRelativesPane() throws ClassNotFoundException {
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

    public void activeGamePane() throws ClassNotFoundException {
        resetAll();
        setActive(gamePane);
        gameController.resetAll();
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

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/englishapplication/view/game.fxml"));
            gamePane = loader.load();
            gameController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            activeSearchPane();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
