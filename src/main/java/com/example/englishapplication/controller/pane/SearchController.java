package com.example.englishapplication.controller.pane;

import com.example.englishapplication.core.DictionaryManagement;
import com.example.englishapplication.core.Word;
import com.example.englishapplication.core.Database;
import com.example.englishapplication.helper.RecommenderSystem;
import com.example.englishapplication.helper.VoiceAPI;
import com.example.englishapplication.stage.PopUpStage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController extends BaseController implements Initializable {
    public ListView<String> listView;
    public TextField searchField;
    public VBox definitionView;
    public ImageView star;
    public Button starButton;
    private List<String> currentSearchWord = new ArrayList<>();

    private ObservableList<String> listWords;

    private void reloadSearchWord() throws ClassNotFoundException {
        String prefix = searchField.getText();

        parallelProcessing(() -> {
            try {
                currentSearchWord.clear();
                currentSearchWord = DictionaryManagement.LookUp(prefix);
                listWords = FXCollections.observableArrayList(currentSearchWord);
                Platform.runLater(() -> listView.setItems(listWords));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        definitionView.getChildren().clear();

        JSONObject meaning = DictionaryManagement.search(prefix);
        if (meaning == null) {
            starButton.setVisible(false);
            String suggestion = RecommenderSystem.getCorrectWord(prefix);
            if (suggestion != null) {
                HBox header = new HBox();
                Label label = new Label("Did you mean: ");
                Button button = new Button(suggestion);
                button.setOnMouseClicked(mouseEvent -> {
                    searchField.setText(suggestion);
                    searchField.positionCaret(searchField.getText().length());
                    try {
                        reloadSearchWord();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
                button.getStyleClass().add("word-suggestion-button");
                label.getStyleClass().add("word-definition-label");
                header.getChildren().addAll(label, button);

                definitionView.getChildren().add(header);
            }
        } else {
            starButton.setVisible(true);
            HBox header = new HBox();
            header.setSpacing(5);
            Label label = new Label(prefix);
            label.getStyleClass().add("word-definition-label");
            header.getChildren().add(label);

            label = new Label(String.format("[%s]", meaning.getString("pronoun")));
            label.getStyleClass().add("word-pronoun-label");
            header.getChildren().add(label);

            ImageView speaker = new ImageView(String.valueOf(getClass().getResource("/com/example/englishapplication/image/speaker.png")));
            speaker.setFitHeight(20);
            speaker.setFitWidth(20);
            speaker.setId("speaker");
            speaker.setOnMouseClicked(mouseEvent -> {
                VoiceAPI.getTextToSpeech(prefix, "en");
            });
            speaker.getStyleClass().add("word-speaker-image");
            header.getChildren().add(speaker);
            definitionView.getChildren().add(header);

            if (Database.existWord(prefix)) {
                star.setImage(starActive.getImage());
            } else {
                star.setImage(starInactive.getImage());
            }
            buildTree(meaning.getJSONArray("type"), 0);
        }
    }
    public void searchFieldAction(KeyEvent keyEvent) throws ClassNotFoundException {
        if (keyEvent.getCode() == keyEvent.getCode().DOWN) {
            if (listView.getSelectionModel().getSelectedIndex() == -1)
                listView.getSelectionModel().selectFirst();
            else if (listView.getSelectionModel().getSelectedIndex() == listView.getItems().size() - 1)
                listView.getSelectionModel().selectFirst();
            else listView.getSelectionModel().selectNext();
        } else if (keyEvent.getCode() == keyEvent.getCode().UP) {
            if (listView.getSelectionModel().getSelectedIndex() == -1)
                listView.getSelectionModel().selectFirst();
            else if (listView.getSelectionModel().getSelectedIndex() == 0)
                listView.getSelectionModel().selectLast();
            else listView.getSelectionModel().selectPrevious();
        } else if (keyEvent.getCode() == keyEvent.getCode().ENTER) {
            listViewAction();
        } else {
            listView.getSelectionModel().clearSelection();
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

        if (Database.existWord(word)) {
            star.setImage(starInactive.getImage());
            Database.removeWord(word);
        } else {
            star.setImage(starActive.getImage());
            String meaning = DictionaryManagement.getMeaning(word);
            Database.addWord(new Word(word, meaning));
        }
    }

    private final ImageView starActive = new ImageView(String.valueOf(getClass().getResource("/com/example/englishapplication/image/star_active.png")));
    private final ImageView starInactive = new ImageView(String.valueOf(getClass().getResource("/com/example/englishapplication/image/star_inactive.png")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private Label setStyleLabel(Label label, int depth) {
        if (depth == 0) {
            label.getStyleClass().add("word-type-label");
        } else if (depth == 1) {
            label.getStyleClass().add("word-explain-label");
        } else if (depth == 2) {
            label.getStyleClass().add("word-example-label");
        }
        label.setWrapText(true);
        return label;
    }
    private void buildTree(JSONArray array, int depth) {
        for (int i = 0; i < array.length(); ++i) {
            JSONObject object = array.getJSONObject(i);
            String name = object.keys().next();
            if (object.get(name) instanceof JSONArray) {
                Label label = new Label("\t".repeat(depth) + name);
                definitionView.getChildren().add(setStyleLabel(label, depth));
                buildTree(object.getJSONArray(name), depth + 1);
            } else {
                Label label = new Label("\t".repeat(depth) + name + ": " + object.get(name));
                definitionView.getChildren().add(setStyleLabel(label, depth));
            }
        }
    }

    public void deleteAction() {
        String prefix = searchField.getText();
        if (DictionaryManagement.isExist(prefix)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete word");
            alert.setHeaderText("Are you sure you want to delete this word?");
            alert.setContentText(prefix);
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    DictionaryManagement.delete(prefix);
                    try {
                        searchField.setText("");
                        reloadSearchWord();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete word");
            alert.setHeaderText("This word is not exist");
            alert.setContentText(prefix);
            alert.showAndWait();
        }
    }

    public void editAction() {
        String prefix = searchField.getText();
        if (DictionaryManagement.isExist(prefix)) {
            new PopUpStage(prefix);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Edit word");
            alert.setHeaderText("This word is not exist");
            alert.setContentText(prefix);
            alert.showAndWait();
        }
    }

    public void addAction() {
        new PopUpStage("");
    }

    @Override
    public void start() {
        super.start();
        searchField.setText("");
        listView.getSelectionModel().clearSelection();
        try {
            reloadSearchWord();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
