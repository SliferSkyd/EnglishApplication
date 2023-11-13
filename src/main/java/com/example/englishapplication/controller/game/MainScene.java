package com.example.englishapplication.controller.game;

import animatefx.animation.*;
import com.example.englishapplication.helper.MediaManager;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static javafx.scene.paint.Color.TRANSPARENT;
import static javafx.scene.paint.Color.WHITE;

public class MainScene extends BaseScene {
    private Rule rule;
    private Question question = new Question();
    private VBox letterBox = new VBox();
    private Text countDownText;
    private JFXButton submitButton;
    private Button soundButton;
    private Text score;
    private Text questionIndex;
    private Text wrongAnswer;

    public MainScene(Stage stage) {
        super(stage);
        MediaManager.playBackgroundMusic(MediaManager.Sound.MAIN, 0.25);

        countDownText = new Text("01:00");

        this.rule = new Rule(stage, countDownText);
        submitButton = new JFXButton("Submit");
        soundButton = new Button("/");
        score = new Text("Score: 0");
        questionIndex = new Text("Question: 0/" + rule.getNumberQuestion());
        wrongAnswer =  new Text("Wrong Answer: 0");

        submitButton.setOnAction(event -> {
            if (question.finished() && question.checkAnswer()) {
                rule.updateScore(true);
                MediaManager.playSoundEffect(MediaManager.Sound.CORRECT);
            } else {
                rule.updateScore(false);
                MediaManager.playSoundEffect(MediaManager.Sound.INCORRECT);
            }
            showQuestion();
        });


        HBox countDownBox = new HBox();
        countDownBox.getStyleClass().add("time");
        countDownText.setFill(WHITE);
        countDownBox.getChildren().add(countDownText);

        HBox submitBox = new HBox();
        submitButton.getStyleClass().add("submit-button");
        submitBox.getChildren().add(submitButton);

        HBox bottomBox = new HBox();
        bottomBox.setPadding(new Insets(0, 23, 12, 0));
        bottomBox.setSpacing(690);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.getChildren().addAll(countDownBox, submitBox);
        root.setBottom(bottomBox);

        HBox infoBox = new HBox();

        if (MediaManager.isSoundEnabled()) soundButton.setTextFill(TRANSPARENT);
        else soundButton.setTextFill(WHITE);

        soundButton.getStyleClass().add("sound");
        soundButton.setOnMouseClicked(event -> {
            if (MediaManager.isSoundEnabled()) {
                MediaManager.stopSound();
                soundButton.setTextFill(WHITE);
            } else {
                MediaManager.playBackgroundMusic(MediaManager.Sound.MAIN, 0.25);
                soundButton.setTextFill(TRANSPARENT);
            }
        });
        soundButton.setTranslateX(WIDTH - 380);
        soundButton.setTranslateY(- 30);

        VBox mainInfoBox = new VBox();
        score.setText("Score: " + rule.getScore());
        score.getStyleClass().add("info-text");
        score.setFill(WHITE);

        questionIndex.setText("Question: " + rule.getCurrentQuestion() + "/" + rule.getNumberQuestion());
        questionIndex.getStyleClass().add("info-text");
        questionIndex.setFill(WHITE);

        wrongAnswer.getStyleClass().add("info-text");
        wrongAnswer.setText("Wrong Answers: " + rule.getNumberWrongAnswer());
        wrongAnswer.setFill(WHITE);

        mainInfoBox.getChildren().addAll(score, questionIndex, wrongAnswer);

        ImageView scoreImage = new ImageView(new Image(getClass().getResourceAsStream("/com/example/englishapplication/image/game/avatar.jpg")));
        scoreImage.setFitWidth(80);
        scoreImage.setFitHeight(80);

        infoBox.getChildren().addAll(scoreImage, mainInfoBox, soundButton);
        infoBox.setSpacing(10);
        root.setTop(infoBox);
        infoBox.setPadding(new Insets(20, 0, 0, 20));

        root.setCenter(letterBox);
        stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, event -> {
            MediaManager.stopSound();
        });
        new FadeIn(root).play();
        showQuestion();
        rule.startCountDown();
    }

    private void showQuestion() {
        question = new Question();

        score.setText("Score: " + rule.getScore());
        questionIndex.setText("Question: " + rule.getCurrentQuestion() + "/" + rule.getNumberQuestion());
        wrongAnswer.setText("Wrong Answers: " + rule.getNumberWrongAnswer());

        letterBox.getChildren().clear();
        letterBox.getChildren().addAll(question.getAnswerLetters(), question.getLetters());
        letterBox.setPadding(new Insets(20, 0, 0, (WIDTH - question.getWordToGuess().length() * 75) / 2));
    }
}