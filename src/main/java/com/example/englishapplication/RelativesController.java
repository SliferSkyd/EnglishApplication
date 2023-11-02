package com.example.englishapplication;

import com.example.englishapplication.base.RelativesAPI;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

    @Override
    public void resetAll() {
    }

    public TextField input;
    public VBox content;
    private void fetchData(JSONObject list, Object key) {
        JSONArray relatives = (JSONArray) list.get((String) key);
        Label label = new Label(key.toString());
        label.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 16px;");

        content.getChildren().add(label);
        TextFlow textFlow = new TextFlow();
        textFlow.setPadding(new javafx.geometry.Insets(0, 10, 0, 0));
        content.getChildren().add(textFlow);
        for (Object relative: relatives) {
            Button relativeWord = new Button(relative.toString());
            relativeWord.setFont(new javafx.scene.text.Font(14));
            relativeWord.setOnAction(actionEvent -> {
                input.setText(relative.toString());
                searchAction();
            });
            relativeWord.getStyleClass().add("word");
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
        content.getChildren().add(loadingLabel);
    }
}