package com.example.englishapplication.controller.pane;

import com.example.englishapplication.stage.Game;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class GameController extends BaseController {
    public ImageView playButton;
    public void playAction() {
        new Game();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void resetAll() throws ClassNotFoundException {

    }
}
