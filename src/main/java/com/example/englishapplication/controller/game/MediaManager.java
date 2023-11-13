package com.example.englishapplication.controller.game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaManager {
    private boolean isSoundEnabled = true;
    private MediaPlayer backgroundMusic;
    private MediaPlayer soundEffect;
    public static final String CORRECT = "/com/example/englishapplication/sound/correct.mp3";
    public static final String INCORRECT = "/com/example/englishapplication/sound/DuckWrong.wav";
    public MediaManager(String backgroundMusicPath) {
        Media backgroundMedia = new Media(getClass().getResource(backgroundMusicPath).toString());
        backgroundMusic = new MediaPlayer(backgroundMedia);
    }

    public void playBackgroundMusic(double num) {
        setSoundEnabled(true);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.setVolume(num);
        backgroundMusic.play();
    }

    public void playSoundEffect(String soundEffectPath) {
        if(!isSoundEnabled()) return;
        Media soundEffectMedia = new Media(MediaManager.class.getResource(soundEffectPath).toString());

        soundEffect = new MediaPlayer(soundEffectMedia);
        soundEffect.play();
        System.out.println(soundEffectPath);
        setSoundEnabled(true);
    }

    public void stopSound() {
        setSoundEnabled(false);
        backgroundMusic.stop();
        if (soundEffect != null) {
            soundEffect.stop();
        }
    }


    public boolean isSoundEnabled() {
        return isSoundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        isSoundEnabled = soundEnabled;
    }
}