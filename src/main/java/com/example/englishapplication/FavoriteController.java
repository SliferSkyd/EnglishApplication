package com.example.englishapplication;

import com.example.englishapplication.base.DictionaryManagement;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FavoriteController extends BaseController implements Initializable {
    public AnchorPane card;
    public Text text;
    private String targetWord, targetMeaning;
    boolean isFrontShowing = true;
    public void cardAction() {
        System.out.println("card action");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void resetAll() throws ClassNotFoundException {
        List<String> words = DictionaryManagement.getAllWords();
        targetMeaning = DictionaryManagement.Search(words.get(0)).toString();
        targetWord = words.get(0);
    }


}
