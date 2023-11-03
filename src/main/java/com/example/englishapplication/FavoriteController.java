package com.example.englishapplication;

import animatefx.animation.*;
import com.example.englishapplication.base.DictionaryManagement;
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
        RotateTransition rotator = new RotateTransition(Duration.millis(1000), card);
        rotator.setAxis(Rotate.X_AXIS);

        if (isFrontShowing) {
            rotator.setFromAngle(0);
            rotator.setToAngle(180);
        } else {
            rotator.setFromAngle(180);
            rotator.setToAngle(0);
        }
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);

        PauseTransition ptChangeCardFace = changeCardFace(card);
        ParallelTransition parallelTransition = new ParallelTransition(rotator, ptChangeCardFace);
        parallelTransition.play();
        isFrontShowing = !isFrontShowing;
    }

    private PauseTransition changeCardFace(AnchorPane card) {
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        if (isFrontShowing) {
            pause.setOnFinished(e -> {
                text.setText(targetMeaning);
                text.setScaleY(-1);
            });
        } else {
            pause.setOnFinished(e -> {
                text.setText(targetWord);
                text.setScaleY(1);
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

    private List<String> words = DictionaryManagement.getAllWords();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void apply(int index) {
        targetMeaning = DictionaryManagement.Search(words.get(index)).toString();
        targetWord = words.get(index);
        text.setText(targetWord);
        text.setScaleY(1);
        isFrontShowing = true;
    }

    @Override
    public void resetAll() {
        card.getScene().addEventHandler(KeyEvent.KEY_RELEASED, this::changeCardAction);
        System.out.println(card.getScene().toString());
        Collections.shuffle(words);
        currentIndex = 0;
        apply(currentIndex);
    }
}