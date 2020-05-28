package com.kodpalmowy.controllers;

import com.kodpalmowy.models.GameFx;
import com.kodpalmowy.models.GameModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class GameController {

    @FXML
    private TextField titleArea;
    @FXML
    private TextField publisherArea;
    @FXML
    private ComboBox<String> genrePick;
    @FXML
    private TextField descriptionArea;
    @FXML
    private ComboBox<String> platformPick;
    @FXML
    private ComboBox<String> modePick;
    @FXML
    private Slider ratingSlider;
    @FXML
    private DatePicker datePick;

    public GameModel gameModel;
    private GameFx gameFx;

    public ObservableList<String> gameGenres = FXCollections.observableArrayList("Action", "Adventure", "Action & Shooting",
            "Indie", "RPG", "Simulator", "Strategy", "MMO", "Casual", "Sports", "Racing", "Horror", "Economy", "Fighting", "Stealth", "Puzzle").sorted();
    public ObservableList<String> gamePlatforms = FXCollections.observableArrayList("PC", "PlayStation 2", "Playstation 3", "Playstation 4",
            "Xbox 360", "Xbox One", "Wii U", "Nintendo Switch", "Playstation Vita", "Playstation Portable", "Mobile").sorted();
    public ObservableList<String> gameModes = FXCollections.observableArrayList("SinglePlayer", "MultiPlayer", "Cooperation").sorted();

    @FXML
    public void initialize() { this.gameModel = new GameModel(); }

    public void setDefaultValues(){
        genrePick.setItems(gameGenres);
        platformPick.setItems(gamePlatforms);
        modePick.setItems(gameModes);
    }
    public void setGame(GameFx gameFx){
        this.gameFx = gameFx;
        titleArea.textProperty().bindBidirectional(gameFx.titleProperty());
        publisherArea.textProperty().bindBidirectional(gameFx.publisherProperty());
        genrePick.valueProperty().bindBidirectional(gameFx.genreProperty());
        descriptionArea.textProperty().bindBidirectional(gameFx.descriptionProperty());
        platformPick.valueProperty().bindBidirectional(gameFx.platformProperty());
        modePick.valueProperty().bindBidirectional(gameFx.modeProperty());
        ratingSlider.valueProperty().bindBidirectional(gameFx.ratingProperty());
        datePick.valueProperty().bindBidirectional(gameFx.releaseDateProperty());
    }

}
