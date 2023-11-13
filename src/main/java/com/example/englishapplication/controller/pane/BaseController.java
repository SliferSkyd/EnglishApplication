package com.example.englishapplication.controller.pane;

import animatefx.animation.FlipInX;
import com.example.englishapplication.core.Utils;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseController extends Utils implements Initializable {
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void start() {
        root.setVisible(true);
        navButton.getStyleClass().add("active");
    }
    public void stop() {
        root.setVisible(false);
        navButton.getStyleClass().removeAll("active");
    }
    protected static Label loadingLabel;
    protected Button navButton;
    protected AnchorPane root;
    public BaseController() {
        loadingLabel = new Label("Loading...");
        loadingLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: red; -fx-font-weight: bold;");
        loadingLabel.setPadding(new javafx.geometry.Insets(10, 10, 0, 0));
    }
    public void setNavButton(Button navButton) {
        this.navButton = navButton;
    }
    public Button getNavButton() {
        return navButton;
    }
    public void setRoot(AnchorPane root) {
        this.root = root;
    }
    public AnchorPane getRoot() {
        return root;
    }
}