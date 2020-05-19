package com.kodpalmowy.controllers;

import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.models.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BookController {

    @FXML
    private TextField titleArea;
    @FXML
    private TextField authorArea;
    @FXML
    public ComboBox<String> genrePick;
    @FXML
    private TextField descriptionArea;
    @FXML
    private TextField isbnArea;
    @FXML
    private TextField publisherArea;
    @FXML
    public Slider ratingSlider;
    @FXML
    public DatePicker datePick;

    public BookModel bookModel;
    private BookFx bookFx;

    public final ObservableList<String> bookGenres = FXCollections.observableArrayList("Drama", "Fairytale", "Poetry", "Satire", "Review", "Religion", "Autobiography", "Diary",
            "True Crime", "Fantasy", "Adventure", "Romance", "Contemporary", "Dystopian", "Mystery",
            "Horror", "Thriller", "Paranormal", "Historical fiction", "Science fiction", "Memoir",
            "Cooking", "Art", "Self-help", "Development", "Motivational", "Health", "History", "Travel",
            "Guide", "Humor", "Children", "Novel", "Learning").sorted();

    @FXML
    public void initialize() {
        this.bookModel = new BookModel();
    }

    public void setDefaultValues() {
        genrePick.setItems(bookGenres);
    }

    public void setBook(BookFx bookFx) {
        this.bookFx = bookFx;
        titleArea.textProperty().bindBidirectional(bookFx.titleProperty());
        authorArea.textProperty().bindBidirectional(bookFx.authorProperty());
        genrePick.valueProperty().bindBidirectional(bookFx.genreProperty());
        descriptionArea.textProperty().bindBidirectional(bookFx.descriptionProperty());
        isbnArea.textProperty().bindBidirectional(bookFx.ISBNProperty());
        publisherArea.textProperty().bindBidirectional(bookFx.publisherProperty());
        ratingSlider.valueProperty().bindBidirectional(bookFx.ratingProperty());
        datePick.valueProperty().bindBidirectional(bookFx.readDateProperty());
    }
}
