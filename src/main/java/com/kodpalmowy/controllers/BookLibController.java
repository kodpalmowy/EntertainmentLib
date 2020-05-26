package com.kodpalmowy.controllers;

import com.kodpalmowy.database.dataAccessObject.BookDao;
import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.utils.DialogUtils;
import com.kodpalmowy.utils.converters.BookConverter;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
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
    @FXML
    private FilterController filterController;

    private final ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private final DialogUtils dialogUtils = new DialogUtils();

    private FilteredList<BookFx> filteredList = new FilteredList<>(bookFxObservableList,value -> true);
    private SortedList<BookFx> sortedList = new SortedList<>(filteredList);

    public enum DialogMode {
        ADD, EDIT
    }

    public BookLibController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableButton(deleteButton, bookTable);
        disableButton(editButton, bookTable);

        BookDao bookDao = new BookDao();
        List<Book> bookList = bookDao.queryBooks();
        obListAddAll(bookList);

        filterController.setBookLibController(this);

        TextField searchField = filterController.getSearchTextField();
        ComboBox<String> genreComboBox = filterController.getGenreComboBox();
        DatePicker dateAfter = filterController.getDateAfter();
        DatePicker dateBefore = filterController.getDateBefore();
        Slider rateSlider = filterController.getRateSlider();

        filterList(searchField,genreComboBox,dateAfter,dateBefore,rateSlider);

        sortedList.comparatorProperty().bind(bookTable.comparatorProperty());
        column_SetCellValueFactory();
        bookTable.setItems(sortedList);
    }

    private void filterList(TextField searchField, ComboBox<String> genreBox, DatePicker dateAfter, DatePicker dateBefore, Slider rateSlider) {
        filteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            LocalDate minDate = dateAfter.getValue();
            LocalDate maxDate = dateBefore.getValue();

            final LocalDate finalMin = minDate == null ? LocalDate.MIN : minDate;
            final LocalDate finalMax = maxDate == null ? LocalDate.MAX : maxDate;
            return book -> (book.getTitle().toLowerCase().contains(searchField.getText().toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(searchField.getText().toLowerCase()) ||
                    book.getDescription().toLowerCase().contains(searchField.getText().toLowerCase())) &&
                    (book.getGenre().equals(genreBox.getValue()) || genreBox.getValue() == null) &&
                    book.getRating() >= rateSlider.getValue() &&
                    (!finalMin.isAfter(book.getReadDate()) && !finalMax.isBefore(book.getReadDate()));
                },
                dateAfter.valueProperty(), dateBefore.valueProperty(), searchField.textProperty(), genreBox.valueProperty(), rateSlider.valueProperty()));
    }

    private void disableButton(Button button, TableView<BookFx> tableView) {
        button.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
    }

    private void obListAddAll(List<Book> bookList) {
        bookList.forEach(book -> {
            BookFx bookFx = BookConverter.convertToBookFx(book);
            bookFxObservableList.add(bookFx);
        });
    }

    private void column_SetCellValueFactory() {
        col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_bookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_bookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_bookDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_bookISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        col_bookPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_bookRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        col_bookReadDate.setCellValueFactory(new PropertyValueFactory<>("readDate"));
    }

    public void handleAddBook(ActionEvent event) {
        handleEditBook(event);
    }

    public void handleEditBook(ActionEvent event) {
        BookFx bookFx = null;
        String DIALOG_TITLE = "";
        DialogMode mode = null;
        if (event.getSource().equals(addButton)) {
            mode = DialogMode.ADD;
            DIALOG_TITLE = "ADD BOOK";
            bookFx = new BookFx();
        } else if (event.getSource().equals(editButton)) {
            mode = DialogMode.EDIT;
            DIALOG_TITLE = "EDIT BOOK";
            bookFx = bookTable.getSelectionModel().getSelectedItem();
        }
        dialogUtils.showDialog(bookFx, DIALOG_TITLE, mode, bookFxObservableList);
    }

    public void handleDelete() {
        BookFx bookFx = bookTable.getSelectionModel().getSelectedItem();
        int bookIdToDelete = bookFx.getBookId();
        String ALERT_HEADER = "Are you sure you want to delete:";
        String ALERT_CONTENT = bookFx.getTitle() + " : " + bookFx.getAuthor();
        Optional<ButtonType> alertResult = dialogUtils.showAlertDialog(ALERT_HEADER, ALERT_CONTENT);
        if (alertResult.orElse(null) == ButtonType.OK) {
            BookDao bookDao = new BookDao();
            bookDao.deleteBook(bookIdToDelete);
            bookFxObservableList.remove(bookFx);
        }
    }
}
