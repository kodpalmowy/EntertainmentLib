package com.kodpalmowy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MovieFilterController {

    @FXML
    private Button clearCountryButton;
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
    private Button clearAllFieldsButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<String> genreComboBox;
    @FXML
    private ComboBox<String> countryComboBox;
    @FXML
    private Slider rateSlider;
    @FXML
    private DatePicker dateAfter;
    @FXML
    private DatePicker dateBefore;

    private MovieLibController movieLibController;

    @FXML
    public void initialize(){
        setComboBoxesValues();
        setVisibleClearButtons();
        enableClearAllFieldsButton();
    }

    private void setComboBoxesValues(){
        GameController gameController = new GameController();
        genreComboBox.setItems(gameController.gameGenres);
    }

    private void enableClearAllFieldsButton(){
        clearAllFieldsButton.disableProperty().bind(searchTextField.textProperty().isEmpty()
                .and(genreComboBox.valueProperty().isNull()
                .and(countryComboBox.valueProperty().isNull()
                .and(rateSlider.valueProperty().isEqualTo(1)
                .and(dateAfter.valueProperty().isNull()
                .and(dateBefore.valueProperty().isNull()))))));
    }

    private void setVisibleClearButtons(){
        clearSearchButton.visibleProperty().bind(searchTextField.textProperty().isNotEmpty());
        clearGenreButton.visibleProperty().bind(genreComboBox.valueProperty().isNotNull());
        clearCountryButton.visibleProperty().bind(countryComboBox.valueProperty().isNotNull());
        clearRateButton.visibleProperty().bind(rateSlider.valueProperty().isNotEqualTo(1));
        clearDateAfterButton.visibleProperty().bind(dateAfter.valueProperty().isNotNull());
        clearDateBeforeButton.visibleProperty().bind(dateBefore.valueProperty().isNotNull());
    }

    public void setMovieLibController(MovieLibController movieLibController) {
        this.movieLibController = movieLibController;
    }

    @FXML
    private void clearSearch() {
        searchTextField.clear();
    }

    @FXML
    private void clearGenre() {
        genreComboBox.setValue(null);
    }

    @FXML
    private void clearRate() {
        rateSlider.setValue(1);
    }

    @FXML
    private void clearDateAfter() {
        dateAfter.setValue(null);
    }

    @FXML
    private void clearDateBefore() {
        dateBefore.setValue(null);
    }

    @FXML
    private void clearCountry() {
        countryComboBox.setValue(null);
    }

    @FXML
    private void clearAllFields() {
        clearSearch();
        clearGenre();
        clearCountry();
        clearRate();
        clearDateAfter();
        clearDateBefore();
    }

    public TextField getSearchTextField() {
        return searchTextField;
    }

    public ComboBox<String> getGenreComboBox() {
        return genreComboBox;
    }

    public ComboBox<String> getCountryComboBox() {
        return countryComboBox;
    }

    public Slider getRateSlider() {
        return rateSlider;
    }

    public DatePicker getDateAfter() {
        return dateAfter;
    }

    public DatePicker getDateBefore() {
        return dateBefore;
    }
}
