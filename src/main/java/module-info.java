module com.example.librarydatabasegui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application to javafx.fxml;
    exports com.application;
}