package com.example.englishapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ContainerController implements Initializable {
    @FXML
    private AnchorPane searchPane;
    @FXML
    private SearchController searchController;
    @FXML
    private AnchorPane mainContent;
    @FXML
    private Button searchButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
