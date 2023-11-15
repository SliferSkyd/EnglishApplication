package com.example.englishapplication.controller.game;

import com.example.englishapplication.core.DictionaryManagement;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Time;

public class Rule {
    private Stage stage;
    private int score = 0;
    private int currentQuestion = 0;
    private int numberWrongAnswer = 0;
    private int numberQuestion = 10;
    private TimeManager timeManager;
    public Rule(Stage stage, Text timeText) {
        this.stage = stage;
        this.timeManager = new TimeManager(this, timeText);
    }
    public void updateScore(boolean isCorrectAnswer) {
        ++currentQuestion;
        if (isCorrectAnswer) {
            score += 10;
        } else {
            ++numberWrongAnswer;
        }
        if (currentQuestion == numberQuestion) {
            endGame();
        }
    }
    public void startCountDown() {
        timeManager.start();
    }
    public int getScore() {
        return score;
    }
    public int getCurrentQuestion() {
        return currentQuestion;
    }
    public int getNumberWrongAnswer() {
        return numberWrongAnswer;
    }
    public int getNumberQuestion() {
        return numberQuestion;
    }
    public void endGame() {
        timeManager.stop();
        if (stage.isShowing()) {
            stage.fireEvent(new GameEvent(GameEvent.GAME_OVER, score, timeManager.getEclipsedTime()));
        }
    }
}