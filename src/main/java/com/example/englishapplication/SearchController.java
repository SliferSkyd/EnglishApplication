package com.example.englishapplication;

import com.example.englishapplication.base.Database;
import com.example.englishapplication.base.DictionaryManagement;
import com.example.englishapplication.base.FuzzySearch;
import com.example.englishapplication.base.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController extends BaseController implements Initializable {
    public ListView<String> listView;
    public TextField searchField;
    public WebView definitionView;
    public ImageView star;
    private List<String> currentSearchWord = new ArrayList<>();

    private ObservableList<String> listWords;

    private void reloadSearchWord() throws ClassNotFoundException {
        String prefix = searchField.getText();
        currentSearchWord.clear();
        currentSearchWord = DictionaryManagement.LookUp(prefix);
        listWords = FXCollections.observableArrayList(currentSearchWord);
        listView.setItems(listWords);
        String meaning = DictionaryManagement.Search(prefix);

        if (meaning == null) {
            star.setVisible(false);
            String correctWord = FuzzySearch.getCorrectWord(prefix);
            if (correctWord.equals("")) {
                definitionView.getEngine().loadContent("No result");
                return;
            }
            definitionView.getEngine().loadContent("Did you mean: <b>" + String.join("</b>, <b>", FuzzySearch.getCorrectWord(prefix)) + "</b>");
        } else {
            definitionView.getEngine().loadContent(meaning);
            star.setVisible(true);
            if (favoriteWords.existWord(prefix)) {
                star.setImage(starActive.getImage());
            } else {
                star.setImage(starInactive.getImage());
            }
        }
    }
    public void searchFieldAction(KeyEvent keyEvent) throws ClassNotFoundException {
        if (keyEvent.getCode() == keyEvent.getCode().DOWN) {
            if (listView.getSelectionModel() == null)
                listView.getSelectionModel().selectFirst();
            else if (listView.getSelectionModel().getSelectedIndex() == listView.getItems().size() - 1)
                listView.getSelectionModel().selectFirst();
            else listView.getSelectionModel().selectNext();
        } else if (keyEvent.getCode() == keyEvent.getCode().UP) {
            if (listView.getSelectionModel() == null)
                listView.getSelectionModel().selectFirst();
            else if (listView.getSelectionModel().getSelectedIndex() == 0)
                listView.getSelectionModel().selectLast();
            else listView.getSelectionModel().selectPrevious();
        } else if (keyEvent.getCode() == keyEvent.getCode().ENTER) {
            listViewAction();
        } else {
            reloadSearchWord();
        }
    }

    public void listViewAction() throws ClassNotFoundException {
        searchField.setText(listView.getSelectionModel().getSelectedItem());
        searchField.positionCaret(searchField.getText().length());
        reloadSearchWord();
    }

    public void starAction() throws ClassNotFoundException {
        String word = searchField.getText();

        if (favoriteWords.existWord(word)) {
            star.setImage(starInactive.getImage());
            favoriteWords.removeWord(word);
        } else {
            star.setImage(starActive.getImage());
            favoriteWords.addWord(word);
        }
    }

    private final ImageView starActive = new ImageView(String.valueOf(getClass().getResource("image/star_active.png")));
    private final ImageView starInactive = new ImageView(String.valueOf(getClass().getResource("image/star_inactive.png")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        try {
            reloadSearchWord();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void resetAll() {

    }
}
