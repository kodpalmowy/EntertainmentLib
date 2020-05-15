package com.kodpalmowy.controllers;

import com.kodpalmowy.database.dao.BookDao;
import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.utils.DialogUtils;
import com.kodpalmowy.utils.converters.BookConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TableView<BookFx> bookTable;
    @FXML
    private TableColumn<BookFx, String> col_bookTitle;
    @FXML
    private TableColumn<BookFx, String> col_bookAuthor;
    @FXML
    private TableColumn<BookFx, String> col_bookGenre;
    @FXML
    private TableColumn<BookFx, String> col_bookDescription;
    @FXML
    private TableColumn<BookFx, String> col_bookISBN;
    @FXML
    private TableColumn<BookFx, String> col_bookPublisher;
    @FXML
    private TableColumn<BookFx, Integer> col_bookRating;
    @FXML
    private TableColumn<BookFx, Date> col_bookReadDate;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;

    private final ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private final DialogUtils dialogUtils = new DialogUtils();

    private String DIALOG_TITLE;
    public enum DialogMode {
        ADD, EDIT
    }

    public BookLibController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteButton.disableProperty().bind(bookTable.getSelectionModel().selectedItemProperty().isNull());
        editButton.disableProperty().bind(bookTable.getSelectionModel().selectedItemProperty().isNull());
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

    public void handleAddBook(ActionEvent event) {
        handleEditBook(event);
    }

    public void handleEditBook(ActionEvent event) {
        BookFx bookFx = null;
        DIALOG_TITLE = "";
        DialogMode mode = null;
        if (event.getSource().equals(addButton)){
            mode = DialogMode.ADD;
            DIALOG_TITLE = "ADD BOOK";
            bookFx = new BookFx();
        } else if (event.getSource().equals(editButton)){
            mode = DialogMode.EDIT;
            DIALOG_TITLE = "EDIT BOOK";
            bookFx = bookTable.getSelectionModel().getSelectedItem();
        }
        dialogUtils.showDialog(bookFx,DIALOG_TITLE, mode, this.bookFxObservableList);
        if (mode == DialogMode.ADD) {
            BookDao bookDao = new BookDao();
            bookFx = BookConverter.convertToBookFx(bookDao.getLastEntry());
            bookFxObservableList.add(bookFx);
        }
    }

    public void handleDelete() {
        BookFx bookFx = bookTable.getSelectionModel().getSelectedItem();
        int bookIdToDelete = bookFx.getBookId();
        String ALERT_TITLE = "Delete Book";
        String ALERT_HEADER = "Are you sure you want to delete:";
        String ALERT_CONTENT = bookFx.getTitle() + " : " + bookFx.getAuthor();
        Optional<ButtonType> alertResult = dialogUtils.showAlertDialog(ALERT_TITLE, ALERT_HEADER, ALERT_CONTENT);
        if (alertResult.orElse(null) == ButtonType.OK) {
            BookDao bookDao = new BookDao();
            bookDao.deleteBook(bookIdToDelete);
            bookFxObservableList.remove(bookFx);
        }
    }
}
