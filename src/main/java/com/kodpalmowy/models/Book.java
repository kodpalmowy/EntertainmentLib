package com.kodpalmowy.models;

import java.util.Date;

public class Book {

    private String title;
    private String author;
    private String genre;
    private String description;
    private String ISBN;
    private String publisher;
    private int rating;
    private Date readDate;

    public Book(String title, String author, String genre, String description, String ISBN, String publisher, int rating, Date readDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.rating = rating;
        this.readDate = readDate;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
}
