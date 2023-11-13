package com.example.englishapplication.controller.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TimeManager {
    private static final int TIME_LIMIT = 90;
    private Rule rule;
    private int totalTime;
    private int remainingTime;
    private Timeline countDown = new Timeline();
    private Text timeText;
    public TimeManager(Rule rule, Text text) {
        this.rule = rule;
        this.totalTime = TIME_LIMIT;
        this.remainingTime = TIME_LIMIT;
        this.timeText = text;
        text.setText(getRemainingTime());
    }
    public void start() {
        this.countDown = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateCountdown()));
        countDown.setCycleCount(Timeline.INDEFINITE);
        countDown.play();
    }
    public void stop() {
        countDown.stop();
    }
    private void updateCountdown() {
        if (remainingTime > 0) {
            --remainingTime;
            timeText.setText(getRemainingTime());
        } else {
            countDown.stop();
            timeText.setText("00:00");
            rule.endGame();
        }
    }
    public String getRemainingTime() {
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    public String getEclipsedTime() {
        int eclipsedTime = totalTime - remainingTime;
        int minutes = eclipsedTime / 60;
        int seconds = eclipsedTime % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}