package com.kodpalmowy.controllers;

import com.kodpalmowy.conecctivity.ConnectionClass;
import com.kodpalmowy.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.ResourceBundle;

public class BookLibController implements Initializable {

    @FXML
    public TableView<Book> bookTable;
    @FXML
    public TableColumn<Book, String> col_bookTitle;
    @FXML
    public TableColumn<Book, String> col_bookAuthor;
    @FXML
    public TableColumn<Book, String> col_bookGenre;
    @FXML
    public TableColumn<Book, String> col_bookDescription;
    @FXML
    public TableColumn<Book, String> col_bookISBN;
    @FXML
    public TableColumn<Book, String> col_bookPublisher;
    @FXML
    public TableColumn<Book, Integer> col_bookRating;
    @FXML
    public TableColumn<Book, Date> col_bookReadDate;

    ObservableList<Book> booksList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection connection = ConnectionClass.getConnection();


        col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_bookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_bookDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_bookISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        col_bookPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_bookRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        col_bookReadDate.setCellValueFactory(new PropertyValueFactory<>("readDate"));
    }
}
