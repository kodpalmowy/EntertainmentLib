package com.kodpalmowy.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class BookFx implements Cloneable {

    private SimpleIntegerProperty bookId = new SimpleIntegerProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty author = new SimpleStringProperty();
    private SimpleStringProperty genre = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty ISBN = new SimpleStringProperty();
    private SimpleStringProperty publisher = new SimpleStringProperty();
    private SimpleIntegerProperty rating = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> readDate = new SimpleObjectProperty<>();

    public BookFx() {
    }

    public int getBookId() {
        return bookId.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public SimpleStringProperty ISBNProperty() {
        return ISBN;
    }

    public SimpleStringProperty publisherProperty() {
        return publisher;
    }

    public SimpleIntegerProperty ratingProperty() {
        return rating;
    }

    public ObjectProperty<LocalDate> readDateProperty() {
        return readDate;
    }

    public void setBookId(int bookId) {
        this.bookId.set(bookId);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getISBN() {
        return ISBN.get();
    }

    public void setISBN(String ISBN) {
        this.ISBN.set(ISBN);
    }

    public String getPublisher() {
        return publisher.get();
    }


    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public int getRating() {
        return rating.get();
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public LocalDate getReadDate() {
        return readDate.get();
    }

    public void setReadDate(LocalDate readDate) {
        this.readDate.set(readDate);
    }

    @Override
    public String toString() {
        return "BookFx{" +
                "_id=" + bookId +
                ", title=" + title +
                ", author=" + author +
                ", genre=" + genre +
                ", description=" + description +
                ", ISBN=" + ISBN +
                ", publisher=" + publisher +
                ", rating=" + rating +
                ", readDate=" + readDate +
                '}';
    }
}
