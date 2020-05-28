package com.kodpalmowy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BookFilterController {

    @FXML
    private Button clearAllFieldsButton;
    @FXML
    private Button clearSearchButton;
    @FXML
    private Button clearGenreButton;
    @FXML
    private Button clearRateButton;
    @FXML
    private Button clearDateAfterButton;
    @FXML
    private Button clearDateBeforeButton;
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
        setVisibleClearButtons();
        enableClearAllFieldsButton();
    }

    private void enableClearAllFieldsButton() {
        clearAllFieldsButton.disableProperty().bind(searchTextField.textProperty().isEmpty()
                .and(genreComboBox.valueProperty().isNull())
                .and(rateSlider.valueProperty().isEqualTo(1))
                .and(dateAfter.valueProperty().isNull()
                .and(dateBefore.valueProperty().isNull())));
    }

    private void setVisibleClearButtons(){
        clearSearchButton.visibleProperty().bind(searchTextField.textProperty().isNotEmpty());
        clearGenreButton.visibleProperty().bind(genreComboBox.valueProperty().isNotNull());
        clearRateButton.visibleProperty().bind(rateSlider.valueProperty().isNotEqualTo(1));
        clearDateAfterButton.visibleProperty().bind(dateAfter.valueProperty().isNotNull());
        clearDateBeforeButton.visibleProperty().bind(dateBefore.valueProperty().isNotNull());
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

    public ComboBox<String> getGenreComboBox() {
        return genreComboBox;
    }

    public DatePicker getDateAfter() {
        return dateAfter;
    }

    public DatePicker getDateBefore() {
        return dateBefore;
    }

    public Slider getRateSlider() {
        return rateSlider;
    }

    @FXML
    public void clearAllFields() {
        clearSearch();
        clearGenre();
        clearRate();
        clearDateAfter();
        clearDateBefore();
    }
    @FXML
    public void clearSearch() {
        searchTextField.clear();
    }
    @FXML
    public void clearGenre() {
        genreComboBox.setValue(null);
    }
    @FXML
    public void clearRate() {
        rateSlider.setValue(1);
    }
    @FXML
    public void clearDateAfter() {
        dateAfter.setValue(null);
    }
    @FXML
    public void clearDateBefore() {
        dateBefore.setValue(null);
    }
}
