package com.example.englishapplication.controller.pane;

import com.example.englishapplication.controller.game.MediaManager;
import com.example.englishapplication.stage.GameStage;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
public class GameController extends BaseController {
    private MediaManager mediaManager = new MediaManager("/com/example/englishapplication/sound/duck.mp3");
    public ImageView playButton;
    public void playAction() {
        mediaManager.stopSound();
        new GameStage();
    }

    @Override
    public void start() {
        super.start();
        mediaManager.playBackgroundMusic(0.25);
    }

    @Override
    public void stop() {
        mediaManager.stopSound();
        super.stop();
    }
}
