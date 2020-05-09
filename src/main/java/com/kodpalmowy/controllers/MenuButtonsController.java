package com.kodpalmowy.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class MenuButtonsController {

    private final String USERS_LIB_FXML = "/fxml/usersMenu.fxml";
    private final String BOOKS_LIB_FXML = "/fxml/bookLib.fxml";
    private final String GAMES_LIB_FXML = "/fxml/gamesLib.fxml";
    private final String MOVIES_LIB_FXML = "/fxml/movieLib.fxml";
    private final String OPTIONS_LIB_FXML = "/fxml/options.fxml";
    @FXML
    private ToggleGroup menuToggleGroup;

    private MainBorderPaneController mainBorderPaneController;

    @FXML
    public void openUsers() {
        mainBorderPaneController.setCenter(USERS_LIB_FXML);
    }

    @FXML
    public void openBookLib() {
        mainBorderPaneController.setCenter(BOOKS_LIB_FXML);
    }

    @FXML
    public void openMovieLib() {
        mainBorderPaneController.setCenter(MOVIES_LIB_FXML);
    }

    @FXML
    public void openGameLib() {
        mainBorderPaneController.setCenter(GAMES_LIB_FXML);
    }

    @FXML
    public void openOptions() {
        mainBorderPaneController.setCenter(OPTIONS_LIB_FXML);
    }

    @FXML
    public void handleExit() {
        if (menuToggleGroup.getSelectedToggle() != null) {
            menuToggleGroup.getSelectedToggle().setSelected(false);
        }
        Platform.exit();
    }

    public void setMainBorderPaneController(MainBorderPaneController mainBorderPaneController) {
        this.mainBorderPaneController = mainBorderPaneController;
    }


}
