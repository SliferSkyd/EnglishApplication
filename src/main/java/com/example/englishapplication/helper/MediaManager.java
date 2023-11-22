package com.example.englishapplication.helper;

import com.example.englishapplication.core.Utils;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaManager extends Utils {
    private static boolean isSoundEnabled = true;
    private static MediaPlayer backgroundMusic;
    private static MediaPlayer soundEffect;
    public enum Sound {
        CORRECT("/com/example/englishapplication/sound/correct.mp3"),
        INCORRECT("/com/example/englishapplication/sound/wrong.wav"),
        GAME_OVER("/com/example/englishapplication/sound/gameover.mp3"),
        INTRODUCE("/com/example/englishapplication/sound/intro.mp3"),
        MAIN("/com/example/englishapplication/sound/main.mp3");
        private String path;
        Sound(String path) {
            this.path = path;
        }
        private String getPath() {
            return path;
        }
    }

    public static void playBackgroundMusic(Sound sound, double num) {
        backgroundMusic = new MediaPlayer(new Media(MediaManager.class.getResource(sound.getPath()).toString()));
        setSoundEnabled(true);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.setVolume(num);
        backgroundMusic.play();
    }

    public static void playSoundEffect(Sound sound) {
        if(!isSoundEnabled()) return;
        soundEffect = new MediaPlayer(new Media(MediaManager.class.getResource(sound.getPath()).toString()));
        soundEffect.play();
        setSoundEnabled(true);
    }

    public static void stopSound() {
        setSoundEnabled(false);
        backgroundMusic.stop();
        if (soundEffect != null) {
            soundEffect.stop();
        }
    }


    public static boolean isSoundEnabled() {
        return isSoundEnabled;
    }

    public static void setSoundEnabled(boolean soundEnabled) {
        isSoundEnabled = soundEnabled;
    }
}