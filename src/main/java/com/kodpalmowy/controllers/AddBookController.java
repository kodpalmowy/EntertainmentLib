package com.kodpalmowy.controllers;

import com.kodpalmowy.models.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AddBookController {
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
    public ComboBox<Integer> ratingPick;
    @FXML
    public DatePicker datePick;

    public BookModel bookModel;

    private final ObservableList<String> bookGenres = FXCollections.observableArrayList("Drama","Fairytale","Poetry","Satire","Review","Religion","Autobiography","Diary",
            "True Crime","Fantasy","Adventure","Romance","Contemporary","Dystopian","Mystery",
            "Horror","Thriller","Paranormal","Historical fiction", "Science fiction","Memoir",
            "Cooking","Art","Self-help","Development","Motivational","Health","History","Travel",
            "Guide","Humor","Children","Novel","Learning").sorted();
    private final ObservableList<Integer> bookRatings = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);

    @FXML
    public void initialize(){
        datePick.setValue(LocalDate.now());
        this.bookModel = new BookModel();
        setBook();
    }

    public void setDefaultValues(){
        genrePick.setItems(bookGenres);
        ratingPick.setItems(bookRatings);
        ratingPick.setValue(null);
        datePick.setValue(LocalDate.now());
    }

    private void setBook() {
        titleArea.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().titleProperty());
        authorArea.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().authorProperty());
        genrePick.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().genreProperty());
        descriptionArea.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().descriptionProperty());
        isbnArea.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().ISBNProperty());
        publisherArea.textProperty().bindBidirectional(bookModel.getBookFxObjectProperty().publisherProperty());
        ratingPick.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().ratingProperty().asObject());
        datePick.valueProperty().bindBidirectional(bookModel.getBookFxObjectProperty().readDateProperty());
    }

}
