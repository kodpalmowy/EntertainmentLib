package com.kodpalmowy.controllers;

import com.kodpalmowy.database.dataAccessObject.MovieDao;
import com.kodpalmowy.database.models.Movie;
import com.kodpalmowy.models.MovieFx;
import com.kodpalmowy.utils.DialogUtils;
import com.kodpalmowy.utils.Utils;
import com.kodpalmowy.utils.converters.MovieConverter;
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

public class MovieLibController implements Initializable {

    @FXML
    private TableView<MovieFx> movieTable;
    @FXML
    private TableColumn<MovieFx, String> col_movieTitle;
    @FXML
    private TableColumn<MovieFx, String> col_movieDirector;
    @FXML
    private TableColumn<MovieFx, String> col_movieGenre;
    @FXML
    private TableColumn<MovieFx, String> col_movieDescription;
    @FXML
    private TableColumn<MovieFx, String> col_movieRating;
    @FXML
    private TableColumn<MovieFx, String> col_movieCountry;
    @FXML
    private TableColumn<MovieFx, String> col_movieReleaseDate;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private MovieFilterController movieFilterController;

    private final ObservableList<MovieFx> movieFxObservableList = FXCollections.observableArrayList();
    private final DialogUtils dialogUtils = new DialogUtils();

    private FilteredList<MovieFx> filteredList = new FilteredList<>(movieFxObservableList);
    private SortedList<MovieFx> sortedList = new SortedList<>(filteredList);

    public MovieLibController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utils.disableButton(editButton, movieTable);
        Utils.disableButton(deleteButton, movieTable);

        MovieDao movieDao = new MovieDao();
        List<Movie> movieList = movieDao.queryMovies();
        addAllToObservableList(movieList);

        movieFilterController.setMovieLibController(this);

        TextField searchField = movieFilterController.getSearchTextField();
        ComboBox<String> genreComboBox = movieFilterController.getGenreComboBox();
        ComboBox<String> countryComboBox = movieFilterController.getCountryComboBox();
        Slider rateSlider = movieFilterController.getRateSlider();
        DatePicker dateAfter = movieFilterController.getDateAfter();
        DatePicker dateBefore = movieFilterController.getDateBefore();

        filterList(searchField, genreComboBox, countryComboBox, rateSlider, dateAfter, dateBefore);
        sortedList.comparatorProperty().bind(movieTable.comparatorProperty());
        column_SetCellValueFactory();
        movieTable.setItems(sortedList);
    }

    private void filterList(TextField searchField, ComboBox<String> genreComboBox, ComboBox<String> countryComboBox, Slider rateSlider, DatePicker dateAfter, DatePicker dateBefore){
        filteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            LocalDate minDate = dateAfter.getValue();
            LocalDate maxDate = dateBefore.getValue();

            final LocalDate finalMin = minDate == null ? LocalDate.MIN : minDate;
            final LocalDate finalMax = maxDate == null ? LocalDate.MAX : maxDate;
            return movie -> (movie.getTitle().toLowerCase().contains(searchField.getText().toLowerCase()) ||
                    movie.getDirector().toLowerCase().contains(searchField.getText().toLowerCase())) &&
                    (movie.getGenre().equals(genreComboBox.getValue()) || genreComboBox.getValue() == null) &&
                    (movie.getCountry().equals(countryComboBox.getValue()) || countryComboBox.getValue() == null) &&
                    movie.getRating() >= rateSlider.getValue() &&
                    (!finalMin.isAfter(movie.getReleaseDate()) && !finalMax.isBefore(movie.getReleaseDate()));
        }, searchField.textProperty(), genreComboBox.valueProperty(), countryComboBox.valueProperty(),
                rateSlider.valueProperty(), dateAfter.valueProperty(), dateBefore.valueProperty()
                ));
    }

    private void column_SetCellValueFactory(){
        col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_movieDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        col_movieGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_movieDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_movieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        col_movieCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        col_movieReleaseDate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    }

    private void addAllToObservableList(List<Movie> movieList){
        movieList.forEach(movie -> {
            MovieFx movieFx = MovieConverter.convertToMovieFx(movie);
            movieFxObservableList.add(movieFx);
        });
    }

    @FXML
    private void handleAddGame(ActionEvent event) {
        handleEditGame(event);
    }

    @FXML
    private void handleEditGame(ActionEvent event) {
        MovieFx movieFx = null;
        BookLibController.DialogMode mode = null;
        if (event.getSource().equals(addButton)){
            mode = BookLibController.DialogMode.ADD;
            movieFx = new MovieFx();
        } else if (event.getSource().equals(editButton)){
            mode = BookLibController.DialogMode.EDIT;
            movieFx = movieTable.getSelectionModel().getSelectedItem();
        }
        dialogUtils.showMovieDialog(movieFx, mode, movieFxObservableList);
    }

    @FXML
    private void handleDeleteGame() {
        MovieFx movieFx = movieTable.getSelectionModel().getSelectedItem();
        int movieIdToDelete = movieFx.getMovieId();
        String ALERT_HEADER = "Are you sure you want to delete:";
        String ALERT_CONTENT = movieFx.getTitle() + " : " + movieFx.getDirector();
        Optional<ButtonType> result = dialogUtils.showAlertDialog(ALERT_HEADER,ALERT_CONTENT);
        if (result.orElse(null) == ButtonType.OK){
            MovieDao movieDao = new MovieDao();
            movieDao.deleteMovie(movieIdToDelete);
            movieFxObservableList.remove(movieFx);
        }
    }
}
