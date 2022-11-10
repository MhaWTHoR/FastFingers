module com.example.fastfingers {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fastfingers to javafx.fxml;
    exports com.example.fastfingers;
    exports com.example.fastfingers.utils;
    opens com.example.fastfingers.utils to javafx.fxml;
    exports com.example.fastfingers.controllers;
    opens com.example.fastfingers.controllers to javafx.fxml;
}