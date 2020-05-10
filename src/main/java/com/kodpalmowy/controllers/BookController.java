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
