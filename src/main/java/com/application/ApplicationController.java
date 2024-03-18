package com.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ApplicationController {

    private static DatabaseConnector databaseConnector;

    @FXML
    private Label queryText; // This is the text next to the buttons that changes based on user input

    @FXML
    private TextField titleTextBox;

    @FXML
    private TextField authorTextBox;

    @FXML
    private TextField isbnTextBox;

    @FXML
    private TextField deweyTextBox;

    @FXML
    private TextField publisherTextBox;

    //Runs when the "Save" button is clicked
    @FXML
    protected void onSaveButtonClick() {
        queryText.setText("Saving...."); //Updating side text to indicate saving.

        BookInput currentInput = buildBookInput(); // Creates a new BookInput based on the info in the form.
    }

    private BookInput buildBookInput() {
        BookInput newInput = new BookInput(); // Creating a new BookInput to store form data

        if (!titleTextBox.getText().isEmpty()) { // If the titleText is not empty
            newInput.setTitle(titleTextBox.getText());
        }
        if (!authorTextBox.getText().isEmpty()) { // If the authorTextBox is not empty
            newInput.setAuthor(authorTextBox.getText());
        }
        if (!isbnTextBox.getText().isEmpty()) { // If the isbnTextBox is not empty
            newInput.setIsbn(isbnTextBox.getText());
        }
        if (!deweyTextBox.getText().isEmpty()) { // If the deweyTextBox is not empty
            newInput.setDeweyNum(deweyTextBox.getText());
        }
        if (!publisherTextBox.getText().isEmpty()) { // If the publisherTextBox is not empty
            newInput.setPublisher(publisherTextBox.getText());
        }

        return newInput;
    }
}