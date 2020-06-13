package com.kodpalmowy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MovieFilterController {

    @FXML
    TextField searchTextField;
    @FXML
    private Button clearSearchButton;
    @FXML
    private ComboBox<String> genreComboBox;
    @FXML
    private Button clearGenreButton;
    @FXML
    private Slider rateSlider;
    @FXML
    private Button clearRateButton;
    @FXML
    private DatePicker dateAfter;
    @FXML
    private Button clearDateAfterButton;
    @FXML
    private DatePicker dateBefore;
    @FXML
    private Button clearDateBeforeButton;
    @FXML
    private Button clearAllFieldsButton;

    @FXML
    private void clearSearch() {
    }

    @FXML
    private void clearGenre() {
    }

    @FXML
    private void clearRate() {
    }

    @FXML
    private void clearDateAfter() {
    }

    @FXML
    private void clearDateBefore() {
    }

    @FXML
    private void clearAllFields() {
    }
}
