package com.kodpalmowy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class SettingsController {
    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    public void initialize() {
        languageComboBox.getItems().add("Polski");
        languageComboBox.getItems().add("English");
        languageComboBox.setValue("English");
    }
}
