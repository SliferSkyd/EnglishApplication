package com.example.englishapplication.controller.pane;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {
    @FXML
    private AnchorPane mainContent;
    @FXML
    private Button searchButton, translateButton, relativesButton, favoriteButton, gameButton;
    private List<BaseController> controllers = new ArrayList<>();

    public void activePaneAction(Event event) {
        Button source = (Button) event.getSource();
        for (int i = 0; i < controllers.size(); ++i) {
            if (controllers.get(i).getRoot().isVisible()) {
                controllers.get(i).stop();
            }
        }
        for (int i = 0; i < controllers.size(); ++i) {
            if (controllers.get(i).getNavButton() == source) {
                controllers.get(i).start();
                break;
            }
        }
    }

    private void loadPane(String url, Button navButton) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        AnchorPane pane = loader.load();
        BaseController controller = loader.getController();
        controller.setNavButton(navButton);
        controller.setRoot(pane);
        pane.setVisible(false);
        mainContent.getChildren().add(pane);
        controllers.add(controller);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadPane("/com/example/englishapplication/view/search.fxml", searchButton);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            loadPane("/com/example/englishapplication/view/translate.fxml", translateButton);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            loadPane("/com/example/englishapplication/view/relatives.fxml", relativesButton);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            loadPane("/com/example/englishapplication/view/favorite.fxml", favoriteButton);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            loadPane("/com/example/englishapplication/view/game.fxml", gameButton);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        controllers.get(0).start();
    }
}
