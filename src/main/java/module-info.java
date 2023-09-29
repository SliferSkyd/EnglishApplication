module com.example.englishapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.web;
    requires javafx.graphics;

    opens com.example.englishapplication to javafx.fxml;
    exports com.example.englishapplication;
}