package com.kodpalmowy.controllers;

import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.models.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

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
    public ComboBox<Integer> ratingPick;
    @FXML
    public DatePicker datePick;

    public BookModel bookModel;
    private BookFx bookFx;

    public final ObservableList<String> bookGenres = FXCollections.observableArrayList("Drama","Fairytale","Poetry","Satire","Review","Religion","Autobiography","Diary",
            "True Crime","Fantasy","Adventure","Romance","Contemporary","Dystopian","Mystery",
            "Horror","Thriller","Paranormal","Historical fiction", "Science fiction","Memoir",
            "Cooking","Art","Self-help","Development","Motivational","Health","History","Travel",
            "Guide","Humor","Children","Novel","Learning").sorted();
    private final ObservableList<Integer> bookRatings = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);

    @FXML
    public void initialize(){
        this.bookModel = new BookModel();
    }

    public void setDefaultValues(){
        genrePick.setItems(bookGenres);
        ratingPick.setItems(bookRatings);
        ratingPick.setValue(1);
        datePick.setValue(LocalDate.now());
    }

    public void setBook(BookFx bookFx){
        this.bookFx = bookFx;
        titleArea.textProperty().bindBidirectional(bookFx.titleProperty());
        authorArea.textProperty().bindBidirectional(bookFx.authorProperty());
        genrePick.valueProperty().bindBidirectional(bookFx.genreProperty());
        descriptionArea.textProperty().bindBidirectional(bookFx.descriptionProperty());
        isbnArea.textProperty().bindBidirectional(bookFx.ISBNProperty());
        publisherArea.textProperty().bindBidirectional(bookFx.publisherProperty());
        ratingPick.valueProperty().bindBidirectional(bookFx.ratingProperty().asObject());
        datePick.valueProperty().bindBidirectional(bookFx.readDateProperty());
    }

}
