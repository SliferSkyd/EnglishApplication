package com.example.englishapplication.controller;

import animatefx.animation.*;
import com.example.englishapplication.core.Word;
import com.example.englishapplication.core.Database;
import javafx.animation.*;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class FavoriteController extends BaseController implements Initializable {
    private int currentIndex = 0;
    public AnchorPane card;
    public Text text;
    private String targetWord, targetMeaning;
    boolean isFrontShowing = true;

    public void cardAction() {
        PauseTransition ptChangeCardFace = changeCardFace();
        RotateTransition rotator = rotateCard();
        ParallelTransition parallelTransition = new ParallelTransition(rotator, ptChangeCardFace);
        parallelTransition.play();
        isFrontShowing = !isFrontShowing;
    }

    private RotateTransition rotateCard() {
        RotateTransition rotator = new RotateTransition(Duration.millis(1000), card);
        rotator.setAxis(Rotate.X_AXIS);

        if (isFrontShowing) {
            rotator.setFromAngle(0);
            rotator.setToAngle(180);
        } else {
            rotator.setFromAngle(180);
            rotator.setToAngle(360);
        }
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }
    private PauseTransition changeCardFace() {
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        if (isFrontShowing) {
            pause.setOnFinished(e -> {
                text.setText(targetMeaning);
                text.setScaleY(text.getScaleY() * -1);
                System.out.println(text.getScaleY());
            });
        } else {
            pause.setOnFinished(e -> {
                text.setText(targetWord);
                text.setScaleY(text.getScaleY() * -1);
                System.out.println(text.getScaleY());
            });
        }
        return pause;
    }

    public void changeCardAction(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("RIGHT")) {
            new BounceInLeft(card).play();
            if (currentIndex == words.size() - 1) {
                currentIndex = 0;
            } else {
                ++currentIndex;
            }
            apply(currentIndex);
            new FlipInX(card).play();
        } else if (keyEvent.getCode().toString().equals("LEFT")) {
            new RotateInDownRight(card).play();
            if (currentIndex == 0) {
                currentIndex = words.size() - 1;
            } else {
                --currentIndex;
            }
            apply(currentIndex);
            new FlipInX(card).play();
        }

    }

    private List<Word> words;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void apply(int index) {
        targetWord = words.get(index).getTarget();
        targetMeaning = words.get(index).getExplain();
        text.setScaleY(1);
        text.setText(targetWord);
        isFrontShowing = true;
    }

    public void removeAction() throws ClassNotFoundException {
        Database.removeWord(words.get(currentIndex).getTarget());
        words.remove(currentIndex);
        if (currentIndex == words.size()) {
            currentIndex = 0;
        }
        resetAll();
    }
    @Override
    public void resetAll() {
        words = Database.getAllWords();
        card.getScene().setOnKeyReleased(this::changeCardAction);
        Collections.shuffle(words);
        new BounceInLeft(card).play();
        apply(currentIndex);
        new FlipInX(card).play();
    }
}