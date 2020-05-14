package com.kodpalmowy.controllers;

import com.kodpalmowy.database.dao.BookDao;
import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.utils.DialogUtils;
import com.kodpalmowy.utils.converters.BookConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookLibController implements Initializable {

    @FXML
    public TableView<BookFx> bookTable;
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

    private final ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private final DialogUtils dialogUtils = new DialogUtils();

    public BookLibController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookDao bookDao = new BookDao();
        List<Book> bookList = bookDao.queryBooks();
        bookList.forEach(book -> {
            BookFx bookFx = BookConverter.convertToBookFx(book);
            bookFxObservableList.add(bookFx);
        });
        col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_bookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_bookDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_bookISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        col_bookPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_bookRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        col_bookReadDate.setCellValueFactory(new PropertyValueFactory<>("readDate"));

        bookTable.setItems(bookFxObservableList);
    }

    public void addBook() {
        BookFx bookFx = dialogUtils.showDialog();
        bookFxObservableList.add(bookFx);
    }

    public void searchBook() {

    }

    public void editBook() {
        dialogUtils.showDialog();
        BookFx bookFx = bookTable.getSelectionModel().getSelectedItem();
        AddBookController controller = new AddBookController();

    }

    public void deleteBook() {
        BookFx bookFx = bookTable.getSelectionModel().getSelectedItem();
        int bookIdToDelete = bookFx.getBookId();
        String ALERT_TITLE = "Delete Book";
        String ALERT_HEADER = "Are you sure you want to delete:";
        String ALERT_CONTENT = bookFx.getTitle() + " : " + bookFx.getAuthor();
        Optional<ButtonType> alertResult = dialogUtils.showAlertDialog(ALERT_TITLE, ALERT_HEADER, ALERT_CONTENT);
        if (alertResult.get() == ButtonType.OK) {
            BookDao bookDao = new BookDao();
            bookDao.deleteBook(bookIdToDelete);
            bookFxObservableList.remove(bookFx);
        }
    }

    public void filterBooks() {

    }
}
