package com.example.englishapplication;

import com.example.englishapplication.base.DictionaryManagement;
import com.example.englishapplication.base.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    ObservableList<String> listWords;
    public void searchFieldAction() {
        String prefix = searchField.getText();
        currentSearchWord.clear();
        currentSearchWord = DictionaryManagement.LookUp(prefix);
        listWords = FXCollections.observableArrayList(currentSearchWord);
        listView.setItems(listWords);
        String meaning = DictionaryManagement.Search(prefix);
        definitionView.getEngine().loadContent(meaning);
    }

    public void listViewAction() {
        searchField.setText(listView.getSelectionModel().getSelectedItem());
        searchFieldAction();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        searchFieldAction();
    }
}
