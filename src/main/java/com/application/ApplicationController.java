package com.application;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationController {

    private static final DatabaseConnector databaseConnector = new DatabaseConnector();

    @FXML
    private Label queryText; // This is the text next to the buttons that changes based on user input

    @FXML
    private TableView tableView; //The Tableview Component

    @FXML
    private TableColumn<BookInput, String> titleColumn; // Define columns for each property
    @FXML
    private TableColumn<BookInput, String> isbnColumn;
    @FXML
    private TableColumn<BookInput, String> authorsColumn;
    @FXML
    private TableColumn<BookInput, String> publisherColumn;
    @FXML
    private TableColumn<BookInput, String> deweyNumColumn;
    @FXML
    private TableColumn<BookInput, String> genreColumn;

    @FXML
    private TextField titleTextBox;

    @FXML
    private TextField authorTextBox;

    @FXML
    private TextField isbnTextBox;

    @FXML
    private TextField deweyTextBox;

    @FXML
    private Label deweyLabel;

    @FXML
    private TextField publisherTextBox;

    @FXML
    private ChoiceBox genreChoiceBox;


    @FXML
    public void initialize() {
        // Set up the table columns and their property values
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        authorsColumn.setCellValueFactory(new PropertyValueFactory<>("authorsOutput"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));


        // Set available choices for the genre choice box
        initGenreBox();

        // Adding a listener to handle row selection events
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Only handle single clicks
                BookInput selectedBook = (BookInput) tableView.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    // Populate the text fields with the selected book's data
                    titleTextBox.setText(selectedBook.getTitle());
                    isbnTextBox.setText(selectedBook.getIsbn());
                    authorTextBox.setText(selectedBook.getAuthorsOutputColon());
                    publisherTextBox.setText(selectedBook.getPublisher());
                    deweyTextBox.setText(selectedBook.getDeweyNum());
                    genreChoiceBox.setValue(selectedBook.getGenre());
                }
            }
        });
    }

    //Runs when the "Save" button is clicked
    @FXML
    protected void onSaveButtonClick() {
        queryText.setText("Saving...."); //Updating status text to indicate saving.

        BookInput currentInput = buildBookInput(); // Creates a new BookInput based on the info in the form.
        try {
            databaseConnector.insertBook(currentInput);
            queryText.setText("Successfully Saved Book"); // Set side text to indicate book saving
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
            queryText.setText("Error Saving"); // Set side text to indicate failure
            e.printStackTrace();
        }
    }

    @FXML
    protected void onClearButtonClick() { //Method to clear all entries in the text boxes
        titleTextBox.clear();
        authorTextBox.clear();
        isbnTextBox.clear();
        deweyTextBox.clear();
        publisherTextBox.clear();
        deweyTextBox.clear();
        genreChoiceBox.setValue(null);
        ObservableList<BookInput> itemsList = tableView.getItems();
        itemsList.clear(); // Clear the Items in the Table as well
    }

    @FXML
    protected void onSearchButtonClick() {
        queryText.setText("Searching...."); // Updating status text to indicate searching.

        BookInput currentInput = buildBookInput(); // Creates a new BookInput based on the info in the form.
        try {
            List<BookInput> result = databaseConnector.searchBook(currentInput); // Retrieve search results from the database
            tableView.getItems().setAll(result); // Set the items in the TableView

            // Set cell value factories to determine how to populate each column

            // For the title column, use the "title" property of the BookInput objects
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

            // For the ISBN column, use the "isbn" property of the BookInput objects
            isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

            // For the authors column, use the "authorsOutput" property of the BookInput objects
            authorsColumn.setCellValueFactory(new PropertyValueFactory<>("authorsOutput"));

            // For the publisher column, use the "publisher" property of the BookInput objects
            publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));

            // For the genre column, use the "genre" property of the BookInput objects
            genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

            // For the deweyNum column, use the "deweyNum" property of the BookInput objects
            deweyNumColumn.setCellValueFactory(new PropertyValueFactory<>("deweyNum"));

            queryText.setText("Search completed"); // Set side text to indicate search completion
        } catch (SQLException e) {
            System.err.println("Error searching data: " + e.getMessage()); // Log error message
            queryText.setText("Error Searching"); // Set side text to indicate error
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        queryText.setText("Deleting...."); //Updating status text to indicate deleting.

        BookInput currentInput = buildBookInput(); // Creates a new BookInput based on the info in the form.
        try {
            databaseConnector.deleteBook(currentInput);
            queryText.setText("Successfully Deleted Book"); // Set side text to indicate book saving
        } catch (SQLException e) {
            System.err.println("Error Deleting data: " + e.getMessage());
            queryText.setText("Error Deleting"); // Set side text to indicate failure
            e.printStackTrace();
        }
    }

    @FXML
    protected void onEditButtonClick() {
        queryText.setText("Editing by ISBN"); // Updating status text to indicate editing.

        BookInput currentInput = buildBookInput(); // Creates a new BookInput based on the info in the form.
        try {
            databaseConnector.editBookByISBN(currentInput);
            queryText.setText("Successfully Edited Book"); // Set side text to indicate book editing
        } catch (SQLException e) {
            System.err.println("Error Editing data: " + e.getMessage());
            queryText.setText("Error Editing"); // Set side text to indicate failure
            e.printStackTrace();
        }
    }
    private BookInput buildBookInput() {
        BookInput newInput = new BookInput(); // Creating a new BookInput to store form data

        if (!titleTextBox.getText().isEmpty()) { // If the titleText is not empty
            newInput.setTitle(titleTextBox.getText());
        }
        if (!authorTextBox.getText().isEmpty()) { // If the authorTextBox is not empty
            ArrayList<String> authors = new ArrayList<>(); //Create a new list of authors
            String[] authorNames = authorTextBox.getText().split("; "); // Create a list of Authors, split by semicolon
            authors.addAll(Arrays.asList(authorNames)); //Add the list to the ArrayList

            newInput.setAuthors(authors);
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
        if (!deweyTextBox.getText().isEmpty()) { // If the deweyTexBox is not empty
            newInput.setDeweyNum(deweyTextBox.getText());
        }
        if (genreChoiceBox.getValue() != null) { // If the genre Choice box is not null
            newInput.setGenre((String)genreChoiceBox.getValue());
        }

        return newInput;
    }
    private void initGenreBox() {
        genreChoiceBox.getItems().addAll("Fiction", "Non-Fiction");

        // Adding a listener to check the value of genre in order to show dewey items
        genreChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Non-Fiction".equals(newValue)) {
                deweyLabel.setVisible(true);
                deweyTextBox.setVisible(true);
            } else {
                deweyLabel.setVisible(false);
                deweyTextBox.clear();
                deweyTextBox.setVisible(false);
            }
        });
    }
}