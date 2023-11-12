package com.example.englishapplication.controller.game;

import animatefx.animation.*;
import com.example.englishapplication.core.DictionaryManagement;
import com.example.englishapplication.core.Utils;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Question extends Utils {
    private HBox letters = new HBox();
    private HBox answerLetters = new HBox();
    private String wordToGuess;

    public Question() {
        wordToGuess = Generate.getRandomWord();
        wordToGuess = shuffle(wordToGuess);
        for (int i = 0; i < wordToGuess.length(); i++) {
            javafx.scene.control.Button letter = new javafx.scene.control.Button("" + wordToGuess.charAt(i));
            letter.getStyleClass().add("letter");
            letter.getStyleClass().add("custom-transparent-button");
            letter.getStyleClass().add("border-button");
            letter.setStyle("-fx-text-fill:#eb4d7d");
            letters.getChildren().add(letter);
        }
        for (int i = 0; i < wordToGuess.length(); i++) {
            javafx.scene.control.Button letter = new Button("_");
            letter.getStyleClass().add("letter");
            letter.getStyleClass().add("custom-transparent-button");
            answerLetters.getChildren().add(letter);
        }
        for (int i = 0; i < letters.getChildren().size(); ++i) {
            Button letter = (Button) letters.getChildren().get(i);
            letter.setOnMouseClicked(event -> {
                if (letter.isVisible()) {
                    for (int j = 0; j < answerLetters.getChildren().size(); ++j) {
                        javafx.scene.control.Button answerLetter = (javafx.scene.control.Button) answerLetters.getChildren().get(j);
                        if (answerLetter.getText() == "_") {
                            answerLetter.setText(letter.getText());
                            answerLetter.getStyleClass().add("border-button");
                            playAnimation(new RollIn(answerLetter), 2);
                            letter.setVisible(false);
                            break;
                        }
                    }
                }
            });
        }
        for (int i = 0; i < answerLetters.getChildren().size(); ++i) {
            javafx.scene.control.Button answerLetter = (javafx.scene.control.Button) answerLetters.getChildren().get(i);
            answerLetter.setOnMouseClicked(event -> {
                if (answerLetter.getText() != "_") {
                    for (int j = 0; j < letters.getChildren().size(); ++j) {
                        javafx.scene.control.Button letter = (javafx.scene.control.Button) letters.getChildren().get(j);
                        if (!letter.isVisible() && letter.getText().equals(answerLetter.getText())) {
                            letter.setVisible(true);
                            playAnimation(new FadeInDown(letter), 1.25);
                            answerLetter.getStyleClass().remove("border-button");
                            answerLetter.setText("_");
                            break;
                        }
                    }
                }
            });
        }
    }
    public String getCurrentWord() {
        String ans = "";
        for (int i = 0; i < answerLetters.getChildren().size(); ++i) {
            javafx.scene.control.Button answerLetter = (javafx.scene.control.Button) answerLetters.getChildren().get(i);
            if (answerLetter.getText() != "_")  ans += answerLetter.getText();
        }
        return ans;
    }
    public boolean finished() {
        return getCurrentWord().length() == wordToGuess.length();
    }
    public boolean checkAnswer() {
        return DictionaryManagement.isExist(getCurrentWord());
    }
    public HBox getLetters() {
        return letters;
    }
    public HBox getAnswerLetters() {
        return answerLetters;
    }
    public String getWordToGuess() {
        return wordToGuess;
    }
}