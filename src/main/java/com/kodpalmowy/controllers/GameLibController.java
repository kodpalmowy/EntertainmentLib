package com.kodpalmowy.controllers;

import com.kodpalmowy.database.dataAccessObject.GameDao;
import com.kodpalmowy.database.models.Game;
import com.kodpalmowy.models.GameFx;
import com.kodpalmowy.utils.DialogUtils;
import com.kodpalmowy.utils.Utils;
import com.kodpalmowy.utils.converters.GameConverter;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
    @FXML
    private GameFilterController gameFilterController;

    private final ObservableList<GameFx> gameFxObservableList = FXCollections.observableArrayList();
    private final DialogUtils dialogUtils = new DialogUtils();

    private FilteredList<GameFx> filteredList = new FilteredList<>(gameFxObservableList);
    private SortedList<GameFx> sortedList = new SortedList<>(filteredList);

    public GameLibController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utils.disableButton(editButton,gameTable);
        Utils.disableButton(deleteButton,gameTable);

        GameDao gameDao = new GameDao();
        List<Game> gameList = gameDao.queryGames();
        addAllToObservableList(gameList);

        gameFilterController.setGameLibController(this);

        TextField searchField = gameFilterController.getSearchTextField();
        ComboBox<String> genreComboBox = gameFilterController.getGenreComboBox();
        ComboBox<String> platformComboBox = gameFilterController.getPlatformComboBox();
        ComboBox<String> modeComboBox = gameFilterController.getModeComboBox();
        Slider rateSlider = gameFilterController.getRateSlider();
        DatePicker dateAfter = gameFilterController.getDateAfter();
        DatePicker dateBefore = gameFilterController.getDateBefore();

        filterList(searchField,genreComboBox,platformComboBox,modeComboBox,rateSlider,dateAfter,dateBefore);
        sortedList.comparatorProperty().bind(gameTable.comparatorProperty());
        column_SetCellValueFactory();
        gameTable.setItems(sortedList);
    }

    private void filterList(TextField searchField, ComboBox<String> genreComboBox, ComboBox<String> platformComboBox, ComboBox<String> modeComboBox, Slider rateSlider, DatePicker dateAfter, DatePicker dateBefore) {
        filteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            LocalDate minDate = dateAfter.getValue();
            LocalDate maxDate = dateAfter.getValue();

            final LocalDate finalMin = minDate == null ? LocalDate.MIN : minDate;
            final LocalDate finalMax = maxDate == null ? LocalDate.MAX : maxDate;

            return game -> (game.getTitle().toLowerCase().equals(searchField.getText().toLowerCase()) ||
                    game.getDescription().toLowerCase().equals(searchField.getText().toLowerCase())) &&
                    (game.getGenre().equals(genreComboBox.getValue()) || genreComboBox.getValue() == null) &&
                    (game.getPlatform().equals(platformComboBox.getValue()) || platformComboBox.getValue() == null) &&
                    (game.getMode().equals(modeComboBox.getValue()) || modeComboBox.getValue() == null) &&
                    game.getRating() >= rateSlider.getValue() &&
                    (!finalMin.isAfter(game.getReleaseDate()) && !finalMax.isBefore(game.getReleaseDate()));
        }, searchField.textProperty(), genreComboBox.valueProperty(), platformComboBox.valueProperty(), modeComboBox.valueProperty(),
                rateSlider.valueProperty(), dateAfter.valueProperty(), dateBefore.valueProperty()
                ));
    }

    private void column_SetCellValueFactory() {
        col_gameTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_gamePublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        col_gameGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_gameDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_gamePlatform.setCellValueFactory(new PropertyValueFactory<>("platform"));
        col_gameMode.setCellValueFactory(new PropertyValueFactory<>("mode"));
        col_gameRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        col_gameReleaseDate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    }

    private void addAllToObservableList(List<Game> gameList){
        gameList.forEach(game -> {
            GameFx gameFx = GameConverter.convertToGameFx(game);
            gameFxObservableList.add(gameFx);
        });
    }

    @FXML
    public void handleAddGame(ActionEvent event) {
        handleEditGame(event);
    }

    @FXML
    public void handleEditGame(ActionEvent event) {
        GameFx gameFx = null;
        BookLibController.DialogMode mode = null;
        if (event.getSource().equals(addButton)){
            mode = BookLibController.DialogMode.ADD;
            gameFx = new GameFx();
        } else if (event.getSource().equals(editButton)){
            mode = BookLibController.DialogMode.EDIT;
            gameFx = gameTable.getSelectionModel().getSelectedItem();
        }
        dialogUtils.showGameDialog(gameFx, mode, gameFxObservableList);
    }

    @FXML
    public void handleDeleteGame() {
        GameFx gameFx = gameTable.getSelectionModel().getSelectedItem();
        int gameIdToDelete = gameFx.getGameId();
        String ALERT_HEADER = "Are you sure you want to delete:";
        String ALERT_CONTENT = gameFx.getTitle() + " : " + gameFx.getPublisher();
        Optional<ButtonType> alertResult = dialogUtils.showAlertDialog(ALERT_HEADER, ALERT_CONTENT);
        if (alertResult.orElse(null) == ButtonType.OK) {
            GameDao gameDao = new GameDao();
            gameDao.deleteGame(gameIdToDelete);
            gameFxObservableList.remove(gameFx);
        }
    }
}
