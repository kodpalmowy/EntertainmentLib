package com.kodpalmowy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class addBookDialogController {

    @FXML
    public TextField titleArea;
    @FXML
    public TextField authorArea;
    @FXML
    public ComboBox<String> genrePick;
    @FXML
    public TextField descriptionArea;
    @FXML
    public TextField isbnArea;
    @FXML
    public TextField publisherArea;
    @FXML
    public ComboBox<Integer> ratingPick;
    @FXML
    public DatePicker datePick;

    public void processResult(){
        String title = titleArea.getText().trim();
        String author = titleArea.getText().trim();

    }

}
