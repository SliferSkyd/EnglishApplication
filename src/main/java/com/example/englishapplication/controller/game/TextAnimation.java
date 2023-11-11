package com.example.englishapplication.controller.game;

import animatefx.animation.*;
import com.example.englishapplication.core.Utils;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TextAnimation extends Utils {
    private HBox letters = new HBox();
    private HBox answerLetters = new HBox();
    private String wordToGuess;
    public TextAnimation() {
        wordToGuess = Generate.getRandomWord();
        for (int i = 0; i < wordToGuess.length(); i++) {
            Button letter = new BorderButton("" + wordToGuess.charAt(i));
            letter.getStyleClass().add("letter");
            letter.getStyleClass().add("custom-transparent-button");
            letter.setStyle("-fx-text-fill:#eb4d7d");
            letters.getChildren().add(letter);
        }
        for (int i = 0; i < wordToGuess.length(); i++) {
            Button letter = new BorderButton("_");
            letter.getStyleClass().add("letter");
            letter.getStyleClass().add("custom-transparent-button");
            answerLetters.getChildren().add(letter);
        }
        for (int i = 0; i < letters.getChildren().size(); ++i) {
            Button letter = (BorderButton) letters.getChildren().get(i);
            letter.setOnMouseClicked(event -> {
                if (letter.isVisible()) {
                    for (int j = 0; j < answerLetters.getChildren().size(); ++j) {
                        Button answerLetter = (Button) answerLetters.getChildren().get(j);
                        if (answerLetter.getText() == "_") {
                            answerLetter.setText(letter.getText());
                            playAnimation(new RollIn(answerLetter), 2);
                            letter.setVisible(false);
                            break;
                        }
                    }
                }
            });
        }
        for (int i = 0; i < answerLetters.getChildren().size(); ++i) {
            Button answerLetter = (Button) answerLetters.getChildren().get(i);
            answerLetter.setOnMouseClicked(event -> {
                if (answerLetter.getText() != "_") {
                    for (int j = 0; j < letters.getChildren().size(); ++j) {
                        Button letter = (Button) letters.getChildren().get(j);
                        if (!letter.isVisible() && letter.getText().equals(answerLetter.getText())) {
                            letter.setVisible(true);
                            playAnimation(new FadeInDown(letter), 1.25);
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
            Button answerLetter = (Button) answerLetters.getChildren().get(i);
            ans += answerLetter.getText();
        }
        return ans;
    }
    public boolean finished() {
        return getCurrentWord().length() == wordToGuess.length();
    }
    public boolean checkAnswer() {
        return getCurrentWord().equals(wordToGuess);
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