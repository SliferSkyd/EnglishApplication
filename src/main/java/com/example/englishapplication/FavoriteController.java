package com.example.englishapplication;

import com.example.englishapplication.base.DictionaryManagement;
import com.example.englishapplication.base.Word;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FavoriteController extends BaseController implements Initializable {

    public FlowPane content;

    public void modifyListWords() throws ClassNotFoundException {
        content.getChildren().clear();
        List<String> words = favoriteWords.getAllWords();
        for (String word: words) {
            Tooltip meaning = new Tooltip(DictionaryManagement.Search(word));
            Button newWord = new Button(word);
            newWord.getStyleClass().add("word");
            newWord.setFont(new javafx.scene.text.Font(18));
            newWord.setTooltip(meaning);
            newWord.setOnAction(actionEvent -> {
                int index = content.getChildren().indexOf(newWord);
                content.getChildren().remove(index);

                AnchorPane frame = new AnchorPane();
                frame.getStyleClass().add("search-box");

                WebView text = new WebView();
                text.setPrefHeight(300);
                text.setPrefWidth(300);

                text.getEngine().loadContent(DictionaryManagement.Search(word));
                frame.getChildren().add(text);

                content.getChildren().add(index, frame);
            });
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
