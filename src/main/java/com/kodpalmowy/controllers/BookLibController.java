package com.kodpalmowy.controllers;

import com.kodpalmowy.connectionUtils.ConnectionClass;
import com.kodpalmowy.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private Connection connection;
    private ResultSet resultSet;

    public BookLibController() {
        connection = ConnectionClass.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM bookList");
            while (resultSet.next()){
                booksList.add(new Book(resultSet.getString("title"),resultSet.getString("author"),
                                       resultSet.getString("genre"),resultSet.getString("description"),
                                       resultSet.getString("ISBN"),resultSet.getString("publisher"),
                                       resultSet.getInt("rating"),resultSet.getDate("ReadDate")));
            }

        } catch (SQLException e){
            System.out.println("SQLException : " + e.getMessage());
        }

        col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_bookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_bookDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_bookISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        col_bookPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_bookRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        col_bookReadDate.setCellValueFactory(new PropertyValueFactory<>("readDate"));

        bookTable.setItems(booksList);
    }

    public void addBook() {
        try {
            Dialog<Button> dialog = new Dialog<>();
            FXMLLoader loader = FXMLLoader.load(getClass().getResource("/fxml/addBookDialog.fxml"));
            dialog.getDialogPane().setContent(loader.load());
            dialog.show();
        } catch (IOException ioe){
            System.out.println("IOException : " + ioe.getMessage());
        }

    }

    public void searchBook() {
    }

    public void editBook() {
    }

    public void deleteBook() {
    }

    public void filterBooks() {

    }
}
