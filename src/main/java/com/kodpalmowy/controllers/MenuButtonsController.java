package com.kodpalmowy.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class MenuButtonsController {

    private final String BOOKS_LIB_FXML = "/fxml/bookLib.fxml";
    private final String GAMES_LIB_FXML = "/fxml/gamesLib.fxml";
    private final String MOVIES_LIB_FXML = "/fxml/movieLib.fxml";
    private final String SETTINGS_LIB_FXML = "/fxml/settings.fxml";
    @FXML
    private ToggleGroup menuToggleGroup;
    @FXML
    private MainController mainController;

    @FXML
    public void openBookLib() {
        mainController.setCenter(BOOKS_LIB_FXML);
    }

    @FXML
    public void openMovieLib() {
        mainController.setCenter(MOVIES_LIB_FXML);
    }

    @FXML
    public void openGameLib() {
        mainController.setCenter(GAMES_LIB_FXML);
    }

    @FXML
    public void openSettings() {
        mainController.setCenter(SETTINGS_LIB_FXML);
    }

    @FXML
    public void handleExit() {
        if (menuToggleGroup.getSelectedToggle() != null) {
            menuToggleGroup.getSelectedToggle().setSelected(false);
        }
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
