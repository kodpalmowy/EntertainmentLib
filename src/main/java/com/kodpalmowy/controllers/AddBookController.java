package com.kodpalmowy.controllers;

import com.kodpalmowy.database.utils.SQLQueries;
import com.kodpalmowy.models.Book;
import com.kodpalmowy.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;

public class AddBookController {
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

    public Book processAddBook(){
        String title = titleArea.getText().trim();
        String author = authorArea.getText().trim();
        String description = descriptionArea.getText().trim();
        String isbn = isbnArea.getText().trim();
        String publisher = publisherArea.getText().trim();
        Date date;
        try {
            date = java.sql.Date.valueOf(datePick.getValue());
        } catch (NullPointerException npe){
            date = null;
            // makes it possible to not to pick date
            // Later change it to something more elegant
        }
        Book book = new Book(title,author,genrePick.getValue(),description,isbn,publisher,ratingPick.getValue(),date);
        SQLQueries.insertBook(book);
        return book;
    }

    public void processEditBook(Book book){
        titleArea.setText(book.getTitle());
        authorArea.setText(book.getAuthor());
        genrePick.setValue(book.getGenre());
        descriptionArea.setText(book.getDescription());
        isbnArea.setText(book.getISBN());
        publisherArea.setText(book.getPublisher());
        ratingPick.setValue(book.getRating());
        datePick.setValue(Utils.convertToLocalDate(book.getReadDate()));
        processAddBook();
    }


}
