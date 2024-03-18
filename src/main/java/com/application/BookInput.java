package com.application;

// A class to consolidate all the input from the GUI so it's easily accessible by the DatabaseConnector class
public class BookInput {

    private String title;
    private String author;
    private String isbn;
    private String deweyNum;
    private String publisher;

    public BookInput() {}
    public BookInput(String title, String author, String isbn, String deweyNum, String publisher) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.deweyNum = deweyNum;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
