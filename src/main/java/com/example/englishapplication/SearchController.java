package com.example.englishapplication;

import com.example.englishapplication.base.DictionaryManagement;
import com.example.englishapplication.base.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    public ListView<String> listView;
    public TextField searchField;
    public WebView definitionView;
    List<String> currentSearchWord = new ArrayList<>();
    public void searchFieldAction() {
        String word = searchField.getText();
        currentSearchWord.clear();
    }

    public void showDefinition() {
        String word = searchField.getText();
        System.out.println(word);
        if (word == null) return;
        String meaning = DictionaryManagement.Search(word);
        definitionView.getEngine().loadContent(meaning);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentSearchWord = DictionaryManagement.LookUp("");
        for (String word: currentSearchWord) {
            listView.getItems().add(word);
            System.out.println(word);
        }
    }

}
