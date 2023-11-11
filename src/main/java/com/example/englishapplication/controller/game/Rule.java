package com.example.englishapplication.controller.game;

import com.example.englishapplication.core.DictionaryManagement;
import javafx.scene.control.Button;

public class Rule {
    private int score = 0;
    private boolean endGame = false;
    private int currentQuestion = 0;
    private int numberWrongAnswer = 0;
    private int numberQuestion = 10;

    public void updateScore(boolean isCorrectAnswer) {
        ++currentQuestion;
        if (isCorrectAnswer) {
            score += 10;
        } else {
            ++numberWrongAnswer;
        }
        if (currentQuestion == numberQuestion) {
            endGame = true;
        }
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
}