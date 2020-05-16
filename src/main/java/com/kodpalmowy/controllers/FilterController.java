package com.kodpalmowy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.time.LocalDate;

public class FilterController {
    @FXML
    private CheckBox genreCheckBox;
    @FXML
    private CheckBox rateCheckBox;
    @FXML
    private CheckBox readAfterCheckBox;
    @FXML
    private CheckBox readBeforeCheckBox;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<String> genreComboBox;
    @FXML
    private Slider rateSlider;
    @FXML
    private DatePicker dateAfter;
    @FXML
    private DatePicker dateBefore;
    @FXML
    private BookLibController bookLibController;

    @FXML
    public void initialize(){
        setGenreComboBox();
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(dateAfter.getValue().plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #4E5F73");
                        }
                    }
                };
            }
        };
        dateBefore.setDayCellFactory(dayCellFactory);
    }

    private void setGenreComboBox(){
        BookController bookController = new BookController();
        genreComboBox.setItems(bookController.bookGenres);
    }

    public void setBookLibController(BookLibController bookLibController) {
        this.bookLibController = bookLibController;
    }

    public TextField getSearchTextField() {
        return searchTextField;
    }

    @FXML
    public void clearFields() {
        searchTextField.clear();
        genreComboBox.setValue(null);
        rateSlider.setValue(1);
        dateAfter.setValue(null);
        dateBefore.setValue(null);
    }
}
