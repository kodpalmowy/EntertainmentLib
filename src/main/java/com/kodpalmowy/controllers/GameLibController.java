package com.kodpalmowy.controllers;

import com.kodpalmowy.models.GameFx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class GameLibController implements Initializable {


    @FXML
    public TableView<GameFx> gameTable;
    @FXML
    public TableColumn<GameFx, String> col_gameTitle;
    @FXML
    public TableColumn<GameFx, String> col_gamePublisher;
    @FXML
    public TableColumn<GameFx, String> col_gameGenre;
    @FXML
    public TableColumn<GameFx, String> col_gameDescription;
    @FXML
    public TableColumn<GameFx, String> col_gameRating;
    @FXML
    public TableColumn<GameFx, String> col_gamePlatform;
    @FXML
    public TableColumn<GameFx, String> col_gameMode;
    @FXML
    public TableColumn<GameFx, String> col_gameReleaseDate;
    @FXML
    public Button addButton;
    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleAddGame(ActionEvent event) {
    }

    @FXML
    public void handleEditGame(ActionEvent event) {
    }

    @FXML
    public void handleDeleteGame(ActionEvent event) {
    }
}
