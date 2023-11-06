package com.example.englishapplication.controller;

import com.example.englishapplication.base.DictionaryManagement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;

public class PopUpController {
    public TextField wordField;
    public TextField phoneticField;
    public PopUpController(String word) {

        wordField.setText(word);
        JSONObject meaning = DictionaryManagement.Search(word);
        if (meaning != null) {
            phoneticField.setText(meaning.getString("phonetic"));
        }
    }
    public AnchorPane pane;
    public void show() {
        Scene scene = new Scene(pane);
        Stage window = new Stage();
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("Add new word");
        window.show();
    }
}
