package com.example.englishapplication.controller.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.TRANSPARENT;
import static javafx.scene.paint.Color.WHITE;

public class UIManager {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private MediaManager mediaManager = new MediaManager("/com/example/englishapplication/sound/cute.mp3");

    private Rule rule;
    private TimeManager timeManager;
    private Stage stage;
    private TextAnimation textAnimation = new TextAnimation();
    private BorderPane root = new BorderPane();
    private VBox letterBox = new VBox();
    private Text countDownText;
    private Button submitButton;
    private Button soundButton;
    private Text score;
    private Text question;
    private Text wrongAnswer;

    public UIManager(Stage stage) {
        this.stage = stage;
        this.rule = new Rule();

        mediaManager.playBackgroundMusic(0.25);

        countDownText = new Text("01:00");
        submitButton = new Button("Submit");
        soundButton = new Button("/");
        score = new Text("Score: 0");
        question = new Text("Question: 0/" + rule.getNumberQuestion());
        wrongAnswer =  new Text("Wrong Answer: 0");

        this.timeManager = new TimeManager(60, this.rule, countDownText);

        submitButton.setOnAction(event -> {
            if (textAnimation.finished()) {
                rule.updateScore(true);
                if (rule.getCurrentQuestion() == rule.getNumberQuestion()) {
                    submitButton.fireEvent(new GameEvent(GameEvent.GAME_OVER));
                } else showQuestion();
            }
        });

        soundButton.setOnAction(event -> {
            mediaManager.playBackgroundMusic(0.25);
        });

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/decorate.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/TimeAndSubmit.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/Inform.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/englishapplication/style/game/GameOver.css").toExternalForm());
        stage.setScene(scene);

        HBox countDownBox = new HBox();
        countDownBox.getStyleClass().add("time");
        countDownText.setFill(WHITE);
        countDownText.setText(timeManager.getRemainingTime());
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

        if (mediaManager.isSoundEnabled()) soundButton.setTextFill(TRANSPARENT);
        else soundButton.setTextFill(WHITE);

        soundButton.getStyleClass().add("sound");

        soundButton.setTranslateX(WIDTH - 380);
        soundButton.setTranslateY(- 30);

        VBox mainInfoBox = new VBox();
        score.setText("Score: " + rule.getScore());
        score.getStyleClass().add("info-text");
        score.setFill(WHITE);
        question.setText("Question: " + rule.getCurrentQuestion() + "/" + rule.getNumberQuestion());
        question.getStyleClass().add("info-text");
        question.setFill(WHITE);
        wrongAnswer.getStyleClass().add("info-text");
        wrongAnswer.setText("Wrong Answers: " + rule.getNumberWrongAnswer());
        wrongAnswer.setFill(WHITE);

        mainInfoBox.getChildren().addAll(score, question, wrongAnswer);

        ImageView scoreImage = new ImageView(new Image(getClass().getResourceAsStream("/com/example/englishapplication/image/game/avatar.jpg")));
        scoreImage.setFitWidth(80);
        scoreImage.setFitHeight(80);

        infoBox.getChildren().addAll(scoreImage, mainInfoBox, soundButton);
        infoBox.setSpacing(10);
        root.setTop(infoBox);
        infoBox.setPadding(new Insets(20, 0, 0, 20));

        root.setCenter(letterBox);

        stage.show();

        showQuestion();
        timeManager.start();
    }

    private void showQuestion() {
        textAnimation = new TextAnimation();

        score.setText("Score: " + rule.getScore());
        question.setText("Question: " + rule.getCurrentQuestion() + "/" + rule.getNumberQuestion());
        wrongAnswer.setText("Wrong Answers: " + rule.getNumberWrongAnswer());

        letterBox.getChildren().clear();
        letterBox.getChildren().addAll(textAnimation.getAnswerLetters(), textAnimation.getLetters());
        letterBox.setPadding(new Insets(20, 0, 0, (WIDTH - textAnimation.getWordToGuess().length() * 75) / 2));
    }
}