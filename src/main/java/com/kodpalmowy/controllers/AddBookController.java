package com.kodpalmowy.controllers;

import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.models.BookModel;
import com.kodpalmowy.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    private BookModel bookModel;

    @FXML
    public void initialize(){

    }
    public Book processAddBook(){
        // Add processing for BookFX
        return null;
    }

    public void setBook(BookFx bookFx) {
//        titleArea.textProperty().bindBidirectional(bookFx.getTitle());
        // This set method might be an editBook method... Watch Erradi tutorial on this.
        // You might wanna add additional getters for BookFx -> check that in tutorial if needed
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
