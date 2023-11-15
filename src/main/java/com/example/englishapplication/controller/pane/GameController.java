package com.example.englishapplication.controller.pane;

import animatefx.animation.*;
import com.example.englishapplication.helper.MediaManager;
import com.example.englishapplication.stage.GameStage;
import javafx.scene.image.ImageView;

public class GameController extends BaseController {
    public ImageView playButton;
    public void playAction() {
        MediaManager.stopSound();
        new GameStage();
    }

    @Override
    public void start() {
        super.start();
        MediaManager.playBackgroundMusic(MediaManager.Sound.INTRODUCE, 0.25);
    }

    @Override
    public void stop() {
        MediaManager.stopSound();
        super.stop();
    }
}
