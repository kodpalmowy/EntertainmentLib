package com.kodpalmowy.controllers;

import com.kodpalmowy.database.utils.ConnectionClass;
import com.kodpalmowy.database.utils.Helper;
import com.kodpalmowy.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
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

    private ObservableList<String> bookGenres = FXCollections.observableArrayList("Drama","Fairytale","Poetry","Satire","Review","Religion","Autobiography","Diary",
            "True Crime","Fantasy","Adventure","Romance","Contemporary","Dystopian","Mystery",
            "Horror","Thriller","Paranormal","Historical fiction", "Science fiction","Memoir",
            "Cooking","Art","Self-help","Development","Motivational","Health","History","Travel",
            "Guide","Humor","Children").sorted();
    private ObservableList<Integer> bookRatings = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);

    private ObservableList<Book> booksList = FXCollections.observableArrayList();
    private Connection connection;


    public BookLibController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM bookList")){
            while (resultSet.next()){
                booksList.add(new Book(resultSet.getString("title"),
                                       resultSet.getString("author"),
                                       resultSet.getString("genre"),
                                       resultSet.getString("description"),
                                       resultSet.getString("ISBN"),
                                       resultSet.getString("publisher"),
                                       resultSet.getInt("rating"),
                                       resultSet.getDate("ReadDate")));

            }
        } catch (SQLException e){
            System.out.println("SQLException : " + e.getMessage());
            e.printStackTrace();
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/addBookDialog.fxml"));
            DialogPane addBookDialog = loader.load();
            BookController bookController = loader.getController();
            bookController.genrePick.setItems(bookGenres);
            bookController.ratingPick.setItems(bookRatings);
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(addBookDialog);
            dialog.setTitle("Add book");

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == ButtonType.OK){
                Book book = bookController.processResult();
                booksList.add(book);
                Helper.insertBook(book);
            }
        } catch (IOException | NoSuchElementException ioe){
            System.out.println("Exception : " + ioe.getMessage());
            ioe.printStackTrace();
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
