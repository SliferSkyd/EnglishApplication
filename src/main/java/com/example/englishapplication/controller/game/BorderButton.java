package com.example.englishapplication.controller.game;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class BorderButton extends Button {
    public BorderButton(String text) {
        super(text);
        this.setOnMouseEntered(event -> {
            if (text != "_") {
                this.getStyleClass().add("border");
            }
        });
        this.setOnMouseExited(event -> {
            if (text != "_") {
                this.getStyleClass().remove("border");
            }
        });
    }
}