module com.example.englishapplication {
    requires javafx.controls;
    requires javafx.fxml;
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
}
