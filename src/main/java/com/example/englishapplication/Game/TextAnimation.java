package com.example.englishapplication.Game;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class TextAnimation {
    private TranslateTransition[] transitions;
    private String word;
    String finalword="";
    HBox buttonContainer = new HBox();
    HBox buttonContainer2 = new HBox();
    List<Button> buttonList = new ArrayList<>();
    List<Button> buttonList2 = new ArrayList<>();

    List<BorderButton> borderButtonList = new ArrayList<>();
    List<BorderButton> borderButtonList2 = new ArrayList<>();

    boolean[] buttonClicked;
    boolean[] buttonClicked2;

    List<Integer> num =new ArrayList<>();

    RandomWord randomWord = new RandomWord();
    public void Text() {
        buttonList.clear();
        buttonList2.clear();
        num.clear();
        buttonContainer2.getChildren().clear();
        buttonContainer.getChildren().clear();
        word = randomWord.WordLetter();
        finalword="";
        buttonClicked = new boolean[word.length()];
        buttonClicked2 = new boolean[word.length()];
        transitions = new TranslateTransition[word.length()];
        buttonContainer = new HBox(word.length());
        buttonContainer2 = new HBox(word.length());



        for (int i = 0; i < word.length(); i++) {
            final int index = i;
            Button button = new Button("" + word.charAt(i));
            button.getStyleClass().add("letter");
            button.getStyleClass().add("custom-transparent-button");
            button.setStyle("-fx-text-fill:#eb4d7d");


            Button button2 = new Button("_");
            button2.getStyleClass().add("letter");
            button2.getStyleClass().add("custom-transparent-button");


            buttonList.add(button);
            buttonContainer.getChildren().add(button);

            buttonList2.add(button2);
            buttonContainer2.getChildren().add(button2);
        }

        for (Button button:buttonList) {
            BorderButton textController = new BorderButton(button);
            borderButtonList.add(textController);
        }

        for (Button button:buttonList2) {
            BorderButton textController2 = new BorderButton(button);
            borderButtonList.add(textController2);
        }

        for (int i=0;i<buttonList.size();i++) {
            final int index = i;
            Button button=buttonList.get(i);
            button.setOnAction(event -> {
                if (!buttonClicked[index]) {
                    //chuyen letter sang line 2
                    Button newButton = (Button) buttonContainer2.getChildren().get(num.size());
                    newButton.setText(button.getText());
                    newButton.setStyle("-fx-text-fill:#000");
                    button.setText("");


                    finalword = finalword + word.charAt(index);
                    buttonClicked[index] = true;
                    buttonClicked2[num.size()]=false;
                    //luu vi tri o line 1
                    num.add(index);
                    //button.setStyle("-fx-text-fill:transparent");
                }
            });
        }

        for (int i=0;i<buttonList2.size();i++) {
            final int index = i;
            Button button=buttonList2.get(i);
            button.setOnAction(event -> {
                if (!buttonClicked2[index] && index==num.size()-1) {
                    //chuyen letter sang line 1
                    Button newButton = (Button) buttonContainer.getChildren().get(num.get(index));
                    newButton.setText(button.getText());
                    newButton.setStyle("-fx-text-fill:#eb4d7d");
                    button.setText("_");

                    if (index >= 0 && index < finalword.length()) {
                        StringBuilder finalwordBuilder = new StringBuilder(finalword);
                        finalwordBuilder.deleteCharAt(index);
                        finalword = finalwordBuilder.toString();
                    }

                    buttonClicked[num.get(index)] = false;
                    buttonClicked2[index]=true;
                    num.remove(index);
                }
            });
        }
    }

    public String getFinalword() {
        return finalword;
    }

    public void setFinalword(String finalword) {
        this.finalword = finalword;
    }

    public HBox getButtonContainer2() {
        return buttonContainer2;
    }

    public void setButtonContainer2(HBox buttonContainer2) {
        this.buttonContainer2 = buttonContainer2;
    }

    public HBox getButtonContainer() {
        return buttonContainer;
    }

    public String getWord() {
        return word;
    }

    public void setButtonContainer(HBox buttonContainer) {
        this.buttonContainer = buttonContainer;
    }
}