module com.example.librarydatabasegui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.librarydatabasegui to javafx.fxml;
    exports com.example.librarydatabasegui;
}