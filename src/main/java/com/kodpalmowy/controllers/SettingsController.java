package com.kodpalmowy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.Locale;

public class SettingsController {
    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    public void initialize() {
        languageComboBox.getItems().add("Polski");
        languageComboBox.getItems().add("English");
        languageComboBox.setValue("English");
    }

    private void setLanguage() {
        String language = "en";
        if (languageComboBox.getSelectionModel().getSelectedItem().equals("Polski")) {
            language = "pl";
        } else {
            language = "en";
        }
        Locale locale = new Locale(language);
        Locale.setDefault(locale);


    }
}
