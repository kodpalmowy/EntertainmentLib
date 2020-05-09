package com.kodpalmowy.controllers;

import com.kodpalmowy.models.Book;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;

public class BookController {
    @FXML
    public TextField titleArea;
    @FXML
    public TextField authorArea;
    @FXML
    public ComboBox<String> genrePick;
    @FXML
    public TextField descriptionArea;
    @FXML
    public TextField isbnArea;
    @FXML
    public TextField publisherArea;
    @FXML
    public ComboBox<Integer> ratingPick;
    @FXML
    public DatePicker datePick;

//    ObservableList<String> bookGenres = FXCollections.observableArrayList("Drama","Fairytale","Poetry","Satire","Review","Religion","Autobiography","Diary",
//            "True Crime","Fantasy","Adventure","Romance","Contemporary","Dystopian","Mystery",
//            "Horror","Thriller","Paranormal","Historical fiction", "Science fiction","Memoir",
//            "Cooking","Art","Self-help","Development","Motivational","Health","History","Travel",
//            "Guide","Humor","Children");
//    ObservableList<Integer> bookRatings = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
//
//    public BookController() {
//        genrePick.setItems(bookGenres);
//        ratingPick.setItems(bookRatings);
//    }

    public Book processResult(){
        String title = titleArea.getText().trim();
        String author = authorArea.getText().trim();
        String description = descriptionArea.getText().trim();
        String isbn = isbnArea.getText().trim();
        String publisher = publisherArea.getText().trim();
        Date date = java.sql.Date.valueOf(datePick.getValue());
        return new Book(title,author,genrePick.getValue(),description,isbn,publisher,ratingPick.getValue(),date);
    }

}
