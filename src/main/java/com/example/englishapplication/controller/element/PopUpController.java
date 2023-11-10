package com.example.englishapplication.controller.element;

import com.example.englishapplication.core.DictionaryManagement;
import com.example.englishapplication.core.Utils;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

public class PopUpController extends Utils {
    public TextField wordField;
    public TextField phoneticField;
    public VBox content;
    private String originalWord;
    public void setWord(String word) {
        originalWord = word;
        wordField.setText(word);
        JSONObject meaning = DictionaryManagement.search(word);
        System.out.println(meaning);
        if (meaning != null) {
            phoneticField.setText(meaning.getString("pronoun"));
            buildTree(meaning.getJSONArray("type"), content, 0);
        }
    }

    private void setText(VBox vBox, String text, int index) {
        if (vBox.getChildren().get(index) instanceof HBox) {
            HBox hBox = (HBox) vBox.getChildren().get(index);
            for (int i = 0; i < hBox.getChildren().size(); ++i) {
                if (hBox.getChildren().get(i) instanceof TextField) {
                    ((TextField) hBox.getChildren().get(i)).setText(text);
                }
            }
        }
    }
    private String getText(VBox vBox, int index) {
        if (vBox.getChildren().get(index) instanceof HBox) {
            HBox hBox = (HBox) vBox.getChildren().get(index);
            for (int i = 0; i < hBox.getChildren().size(); ++i) {
                if (hBox.getChildren().get(i) instanceof TextField) {
                    return ((TextField) hBox.getChildren().get(i)).getText();
                }
            }
        }
        return null;
    }
    private void buildTree(JSONArray array, Object parent, int depth) {
        for (int i = 0; i < array.length(); ++i) {
            JSONObject object = array.getJSONObject(i);
            String name = object.keys().next();
            VBox current = null;
            if (object.get(name) instanceof JSONArray) {
                if (depth == 0) {
                    current = addTypeAction();
                    setText(current, name, 0);
                } else if (depth == 1) {
                    current = addMeaningAction((VBox) parent);
                    setText(current, name, 0);
                }
                buildTree(object.getJSONArray(name), current, depth + 1);
            } else {
                current = addExampleAction((VBox) parent);
                setText(current, name, 0);
                setText(current, object.getString(name), 1);
            }
        }
    }

    public void saveAction() {
        String word = wordField.getText();
        String phonetic = phoneticField.getText();
        JSONObject meaning = new JSONObject();
        meaning.put("pronoun", phonetic);
        JSONArray typeArray = new JSONArray();
        for (int i = 0; i < content.getChildren().size(); ++i) {
            VBox typeVBox = (VBox) content.getChildren().get(i);
            JSONObject typeObject = new JSONObject();
            String type = getText(typeVBox, 0);
            typeObject.put(type, new JSONArray());
            JSONArray meaningArray = typeObject.getJSONArray(type);
            for (int j = 1; j < typeVBox.getChildren().size(); ++j) {
                VBox meaningVBox = (VBox) typeVBox.getChildren().get(j);
                JSONObject meaningObject = new JSONObject();
                String meaningString = getText(meaningVBox, 0);
                meaningObject.put(meaningString, new JSONArray());
                JSONArray exampleArray = meaningObject.getJSONArray(meaningString);
                for (int k = 1; k < meaningVBox.getChildren().size(); ++k) {
                    VBox exampleVBox = (VBox) meaningVBox.getChildren().get(k);
                    String englishExample = getText(exampleVBox, 0);
                    String vietnameseExample = getText(exampleVBox, 1);
                    JSONObject exampleObject = new JSONObject();
                    exampleObject.put(englishExample, vietnameseExample);
                    exampleArray.put(exampleObject);
                }
                meaningArray.put(meaningObject);
            }
            typeArray.put(typeObject);
        }
        meaning.put("type", typeArray);
        System.out.println(meaning.toString());
        if (confirm("Are you sure you want to save this word?", word)) {
            if (originalWord != null) {
                DictionaryManagement.delete(originalWord);
            }
            DictionaryManagement.add(word, meaning);
            Stage thisStage = (Stage) content.getScene().getWindow();
            thisStage.close();
        }

        if (originalWord != null) {
            DictionaryManagement.delete(originalWord);
        }
        DictionaryManagement.add(word, meaning);
    }
    public VBox addTypeAction() {
        VBox typeVBox = new VBox();
        HBox typeHBox = new HBox();
        ImageView addMeaningButton = new ImageView(String.valueOf(getClass().getResource("/com/example/englishapplication/image/add.png")));
        addMeaningButton.setFitHeight(20);
        addMeaningButton.setFitWidth(20);
        addMeaningButton.setOnMouseClicked(event -> {
            addMeaningAction(typeVBox);
        });
        typeHBox.getChildren().add(addMeaningButton);

        Label typeLabel = new Label("Type");
        typeLabel.setPrefHeight(20);
        typeHBox.getChildren().add(typeLabel);

        TextField typeField = new TextField();
        typeField.setPromptText("Type");
        typeField.setPrefHeight(20);
        typeField.setPrefWidth(255);
        typeHBox.getChildren().add(typeField);

        ImageView removeTypeButton = new ImageView(String.valueOf(getClass().getResource("/com/example/englishapplication/image/remove.png")));
        removeTypeButton.setFitHeight(20);
        removeTypeButton.setFitWidth(20);
        removeTypeButton.setOnMouseClicked(event -> {
            content.getChildren().remove(typeVBox);
        });
        typeHBox.getChildren().add(removeTypeButton);

        typeVBox.getChildren().add(typeHBox);
        content.getChildren().add(typeVBox);
        return typeVBox;
    }

    public VBox addMeaningAction(VBox typeBox) {
        VBox meaningVBox = new VBox();
        HBox meaningHBox = new HBox();
        ImageView addExampleButton = new ImageView(String.valueOf(getClass().getResource("/com/example/englishapplication/image/add.png")));
        addExampleButton.setFitHeight(20);
        addExampleButton.setFitWidth(20);
        addExampleButton.setOnMouseClicked(event -> {
            addExampleAction(meaningVBox);
        });
        meaningHBox.getChildren().add(addExampleButton);

        Label meaningLabel = new Label("Meaning");
        meaningLabel.setPrefHeight(20);
        meaningHBox.getChildren().add(meaningLabel);

        TextField meaningField = new TextField();
        meaningField.setPromptText("Meaning");
        meaningField.setPrefHeight(20);
        meaningField.setPrefWidth(223);
        meaningHBox.getChildren().add(meaningField);

        ImageView removeMeaningButton = new ImageView(String.valueOf(getClass().getResource("/com/example/englishapplication/image/remove.png")));
        removeMeaningButton.setFitHeight(20);
        removeMeaningButton.setFitWidth(20);
        removeMeaningButton.setOnMouseClicked(event -> {
            typeBox.getChildren().remove(meaningVBox);
        });
        meaningHBox.getChildren().add(removeMeaningButton);

        meaningVBox.getChildren().add(meaningHBox);
        typeBox.getChildren().add(meaningVBox);
        return meaningVBox;
    }

    public VBox addExampleAction(VBox meaningBox) {
        VBox exampleVBox = new VBox();
        HBox exampleHBox = new HBox();

        Label EnglishExampleLabel = new Label("Example");
        EnglishExampleLabel.setPrefHeight(20);
        exampleHBox.getChildren().add(EnglishExampleLabel);

        TextField EnglishExampleField = new TextField();
        EnglishExampleField.setPromptText("English Example");
        EnglishExampleField.setPrefHeight(20);
        EnglishExampleField.setPrefWidth(207);
        exampleHBox.getChildren().add(EnglishExampleField);

        ImageView removeExampleButton = new ImageView(String.valueOf(getClass().getResource("/com/example/englishapplication/image/remove.png")));
        removeExampleButton.setFitHeight(20);
        removeExampleButton.setFitWidth(20);
        removeExampleButton.setOnMouseClicked(event -> {
            meaningBox.getChildren().remove(exampleVBox);
        });
        exampleHBox.getChildren().add(removeExampleButton);

        exampleVBox.getChildren().add(exampleHBox);

        exampleHBox = new HBox();

        TextField VietnameseExampleField = new TextField();
        VietnameseExampleField.setPromptText("Vietnamese Example");
        VietnameseExampleField.setPrefHeight(20);
        VietnameseExampleField.setPrefWidth(207);
        exampleHBox.getChildren().add(VietnameseExampleField);

        removeExampleButton = new ImageView();
        removeExampleButton.setFitHeight(20);
        removeExampleButton.setFitWidth(20);
        exampleHBox.getChildren().add(removeExampleButton);

        exampleVBox.getChildren().add(exampleHBox);
        
        meaningBox.getChildren().add(exampleVBox);
        return exampleVBox;
    }
}
