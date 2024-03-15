package com.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ApplicationController {
    @FXML
    private Label queryText; // This is the text next to the buttons that changes based on user input

    @FXML
    protected void onSaveButtonClick() {
        queryText.setText("Welcome to JavaFX Application!");
    }
}