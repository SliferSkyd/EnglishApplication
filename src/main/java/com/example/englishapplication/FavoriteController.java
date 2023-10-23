package com.example.englishapplication;

import com.example.englishapplication.base.Word;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FavoriteController extends BaseController implements Initializable {

    public VBox content;

    public void modifyListWords() throws ClassNotFoundException {
        content.getChildren().clear();
        List<String> words = favoriteWords.getAllWords();
        for (String word: words) {
            VBox newWord = new VBox();

            content.getChildren().add(newWord);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            modifyListWords();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void resetAll() throws ClassNotFoundException {
        modifyListWords();
    }
}
