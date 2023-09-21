module com.example.englishapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.englishapplication to javafx.fxml;
    exports com.example.englishapplication;
}