module com.example.englishapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.graphics;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires okhttp3;
    requires org.json;
    requires org.apache.commons.text;
    requires json.simple;
    requires AnimateFX;
    requires com.jfoenix;

    opens com.example.englishapplication to javafx.fxml;
    exports com.example.englishapplication;
    exports com.example.englishapplication.core;
    opens com.example.englishapplication.core to javafx.fxml;

    exports com.example.englishapplication.stage;
    opens com.example.englishapplication.stage to javafx.fxml;
    exports com.example.englishapplication.controller;
    opens com.example.englishapplication.controller to javafx.fxml;
    exports com.example.englishapplication.helper;
    opens com.example.englishapplication.helper to javafx.fxml;
    exports com.example.englishapplication.Game;
}
