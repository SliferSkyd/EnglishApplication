package com.example.englishapplication;

import com.example.englishapplication.base.RelativesAPI;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class RelativesController extends BaseController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        content.setSpacing(10);
    }

    public TextField input;
    public HBox content;
    private void fetchData(JSONObject list, Object key) {
        JSONArray relatives = (JSONArray) list.get((String) key);
        Label label = new Label(key.toString());
        content.getChildren().add(label);
        TextFlow textFlow = new TextFlow();
        content.getChildren().add(textFlow);
        for (Object relative : relatives) {
            Button relativeWord = new Button(relative.toString());
            relativeWord.setOnAction(actionEvent -> {
                input.setText(relative.toString());
                searchAction();
            });
            textFlow.getChildren().add(relativeWord);
        }
    }

    public void searchAction() {
        Thread thread = new Thread(() -> {
            JSONObject list = RelativesAPI.getList(input.getText());
            Platform.runLater(() -> {
                content.getChildren().clear();
                for (Object key : list.keySet()) {
                    fetchData(list, key);
                }
            });
        });
        thread.setDaemon(true);
        thread.start();
        content.getChildren().clear();
        content.getChildren().add(new Label("Loading..."));
    }

}
