package com.kodpalmowy.controllers;

import com.kodpalmowy.database.utils.ConnectionClass;
import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.utils.DialogUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class BookLibController implements Initializable {

    @FXML
    public TableView<BookFx> bookTable;
    @FXML
    public TableColumn<BookFx, Integer> col_bookID;
    @FXML
    public TableColumn<BookFx, String> col_bookTitle;
    @FXML
    public TableColumn<BookFx, String> col_bookAuthor;
    @FXML
    public TableColumn<BookFx, String> col_bookGenre;
    @FXML
    public TableColumn<BookFx, String> col_bookDescription;
    @FXML
    public TableColumn<BookFx, String> col_bookISBN;
    @FXML
    public TableColumn<BookFx, String> col_bookPublisher;
    @FXML
    public TableColumn<BookFx, Integer> col_bookRating;
    @FXML
    public TableColumn<BookFx, Date> col_bookReadDate;

    private ObservableList<BookFx> booksList = FXCollections.observableArrayList();
    private Connection connection;
    private DialogUtils dialogUtils = new DialogUtils();

    public BookLibController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM bookList")){
//            while (resultSet.next()){
//                booksList.add(new BookFx( resultSet.getString("title"),
//                                        resultSet.getString("author"),
//                                        resultSet.getString("genre"),
//                                        resultSet.getString("description"),
//                                        resultSet.getString("ISBN"),
//                                        resultSet.getString("publisher"),
//                                        resultSet.getInt("rating"),
//                                        resultSet.getDate("ReadDate")));
//            }
            // CHECK THAT! This might return Book object and later return converted BookFx object but i dunno for now
        } catch (SQLException e){
            System.out.println("SQLException (INITIALIZE) : " + e.getMessage());
            e.printStackTrace();
        }
        col_bookID.setCellValueFactory(new PropertyValueFactory<>("_id"));
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
//        BookFx book = dialogUtils.showDialog(DialogUtils.DIALOG_MODE.ADD);
//        booksList.add(BookFx);
        /**
         *  TO BE CHANGED ...
         */
    }

    public void searchBook() {

    }

    public void editBook() {
        dialogUtils.showDialog(DialogUtils.DIALOG_MODE.EDIT);
//        Book book = bookTable.getSelectionModel().getSelectedItem();
        AddBookController controller = new AddBookController();
//        controller.processEditBook(book);
        /**
         *  TO BE CHANGED ...
         */
    }

    public void deleteBook() {
    }

    public void filterBooks() {

    }
}
