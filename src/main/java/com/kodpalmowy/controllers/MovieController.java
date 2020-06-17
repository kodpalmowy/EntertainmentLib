package com.kodpalmowy.controllers;

import com.kodpalmowy.models.MovieFx;
import com.kodpalmowy.models.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MovieController {

    @FXML
    private TextField titleArea;
    @FXML
    private TextField directorArea;
    @FXML
    private ComboBox<String> genrePick;
    @FXML
    private ComboBox<String> countryPick;
    @FXML
    private TextField descriptionArea;
    @FXML
    private Slider ratingSlider;
    @FXML
    private DatePicker datePick;

    public MovieModel movieModel;
    private MovieFx movieFx;

    public final ObservableList<String> movieGenres = FXCollections.observableArrayList("Action", "Animation", "documentary",
            "Family", "Fantasy", "Horror", "Comedy", "Short-movie", "Crime", "Adventure", "Romance", "Sci-Fi", "Thriller",
            "Anime", "Bible", "Biography", "Kids", "Drama", "Erotic", "Noir", "Historical", "Romantic Comedy", "Musical",
            "Political", "Sensational", "Sport", "Spy", "Martial Arts", "Western", "War", "XXX").sorted();

    @FXML
    public void initialize(){
        this.movieModel = new MovieModel();
    }

    public void setDefaultValues(){
        genrePick.setItems(movieGenres);
    }

    public void setMovie(MovieFx movieFx){
        this.movieFx = movieFx;
        titleArea.textProperty().bindBidirectional(movieFx.titleProperty());
        directorArea.textProperty().bindBidirectional(movieFx.directorProperty());
        genrePick.valueProperty().bindBidirectional(movieFx.genreProperty());
        countryPick.valueProperty().bindBidirectional(movieFx.countryProperty());
        descriptionArea.textProperty().bindBidirectional(movieFx.descriptionProperty());
        ratingSlider.valueProperty().bindBidirectional(movieFx.ratingProperty());
        datePick.valueProperty().bindBidirectional(movieFx.releaseDateProperty());
    }
}
