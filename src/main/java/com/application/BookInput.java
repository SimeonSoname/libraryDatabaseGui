package com.application;

import java.util.ArrayList;

// A class to consolidate all the input from the GUI so it's easily accessible by the DatabaseConnector class
public class BookInput {

    private String title;
    private ArrayList<String> authors = new ArrayList<>();
    private String authorsOutput;
    private String isbn;
    private String deweyNum;
    private String publisher;
    private String genre;

    public BookInput() {}


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDeweyNum() {
        return deweyNum;
    }

    public void setDeweyNum(String deweyNum) {
        this.deweyNum = deweyNum;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthorsOutput() {
        return authorsOutput;
    }

    public String getAuthorsOutputColon() {
       return authorsOutput.replace(",",";");
    }
    public void setAuthorOutput(String authorOutput) {
        this.authorsOutput = authorOutput;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
