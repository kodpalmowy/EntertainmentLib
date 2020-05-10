package com.kodpalmowy.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    private MenuButtonsController menuButtonsController;
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private void initialize(){
        menuButtonsController.setMainController(this);
    }

    public void setCenter(String fxml){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.language");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml),resourceBundle);
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        mainBorderPane.setCenter(parent);
    }
}