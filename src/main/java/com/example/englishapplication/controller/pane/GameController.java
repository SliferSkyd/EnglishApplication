package com.example.englishapplication.controller.pane;

import io.github.palexdev.materialfx.controls.MFXListView;

import java.net.URL;
import java.util.ResourceBundle;
public class GameController extends BaseController {
    public MFXListView<String> listView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().add("Hello");
    }

    @Override
    public void resetAll() throws ClassNotFoundException {

    }
}
