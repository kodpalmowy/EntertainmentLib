package com.kodpalmowy.utils;

import com.kodpalmowy.controllers.BookController;
import com.kodpalmowy.controllers.BookLibController;
import com.kodpalmowy.controllers.GameController;
import com.kodpalmowy.controllers.MovieController;
import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.models.GameFx;
import com.kodpalmowy.models.MovieFx;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DialogUtils {

    private final BookFx temporaryBookFx = new BookFx();
    private final GameFx temporaryGameFx = new GameFx();
    private final MovieFx temporaryMovieFx = new MovieFx();

    private static double xOffset = 0.0;
    private static double yOffset = 0.0;

    private final String BOOK_DIALOG_FXML_PATH = "/fxml/bookDialog.fxml";
    private final String GAME_DIALOG_FXML_PATH = "/fxml/gameDialog.fxml";
    private final String MOVIE_DIALOG_FXML_PATH = "/fxml/movieDialog.fxml";

    public void showBookDialog(BookFx bookFx, BookLibController.DialogMode mode, ObservableList<BookFx> obList) {
        try {
            FXMLLoader loader = getLoader(BOOK_DIALOG_FXML_PATH);
            DialogPane dialogPane = loader.load();
            BookController bookController = loader.getController();
            bookController.setDefaultValues();

            Utils.copyBookFx(bookFx, temporaryBookFx);
            bookController.setBook(temporaryBookFx);

            Dialog<BookFx> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            disableOkButton(temporaryBookFx, dialog);
            setWindowStyles(dialogPane);
            dialog.setResultConverter(button -> button == ButtonType.OK ? temporaryBookFx : null);
            dialog.showAndWait().ifPresent(result -> {
                Utils.copyBookFx(result, bookFx);
                if (mode == BookLibController.DialogMode.ADD) {
                    bookController.bookModel.saveBookInDB(bookFx);
                    obList.add(bookFx);
                } else if (mode == BookLibController.DialogMode.EDIT) {
                    bookController.bookModel.updateBookInDB(bookFx);
                }
            });
        } catch (IOException ioe) {
            System.out.println("Exception (showBookDialog) : " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public void showGameDialog(GameFx gameFx, BookLibController.DialogMode mode, ObservableList<GameFx> obList) {
        try {
            FXMLLoader loader = getLoader(GAME_DIALOG_FXML_PATH);
            DialogPane dialogPane = loader.load();
            GameController gameController = loader.getController();
            gameController.setDefaultValues();

            Utils.copyGameFx(gameFx, temporaryGameFx);
            gameController.setGame(temporaryGameFx);

            Dialog<GameFx> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            disableOkButton(temporaryGameFx, dialog);
            setWindowStyles(dialogPane);
            dialog.setResultConverter(button -> button == ButtonType.OK ? temporaryGameFx : null);
            dialog.showAndWait().ifPresent(result -> {
                Utils.copyGameFx(result, gameFx);
                if (mode == BookLibController.DialogMode.ADD){
                    gameController.gameModel.saveGameInDB(gameFx);
                    obList.add(gameFx);
                } else if (mode == BookLibController.DialogMode.EDIT){
                    gameController.gameModel.updateGameInDB(gameFx);
                }
            });

        } catch (IOException ioe){
            System.out.println("Exception (showGameDialog) : " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public void showMovieDialog(MovieFx movieFx, BookLibController.DialogMode mode, ObservableList<MovieFx> obList) {
        try {
            FXMLLoader loader = getLoader(MOVIE_DIALOG_FXML_PATH);
            DialogPane dialogPane = loader.load();
            MovieController movieController = loader.getController();
            movieController.setDefaultValues();

            Utils.copyMovieFx(movieFx, temporaryMovieFx);
            movieController.setMovie(temporaryMovieFx);

            Dialog<MovieFx> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            disableOkButton(temporaryMovieFx, dialog);
            setWindowStyles(dialogPane);
            dialog.setResultConverter(button -> button == ButtonType.OK ? temporaryMovieFx : null);
            dialog.showAndWait().ifPresent(result -> {
                Utils.copyMovieFx(result, movieFx);
                if (mode == BookLibController.DialogMode.ADD){
                    movieController.movieModel.saveMovieInDB(movieFx);
                    obList.add(movieFx);
                } else if (mode == BookLibController.DialogMode.EDIT){
                    movieController.movieModel.updateMovieInDB(movieFx);
                }
            });

        } catch (IOException ioe){
            System.out.println("Exception (showMovieDialog) : " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    private FXMLLoader getLoader(String FXML_PATH) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.language");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_PATH));
        loader.setResources(resourceBundle);
        return loader;
    }

    private void setWindowStyles(DialogPane dialogPane) {
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        dialogPane.getScene().setFill(Color.TRANSPARENT);

        dialogPane.setOnMousePressed(mouseEvent -> {
            xOffset = stage.getX() - mouseEvent.getScreenX();
            yOffset = stage.getY() - mouseEvent.getScreenY();
        });
        dialogPane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() + xOffset);
            stage.setY(mouseEvent.getScreenY() + yOffset);
        });

    }

    public Optional<ButtonType> showAlertDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane alertDialogPane = alert.getDialogPane();
        alertDialogPane.getStylesheets()
                .add(getClass().getResource("/css/dialog_stylesheet.css")
                        .toExternalForm());
        alertDialogPane.getStyleClass().add("dialog-pane");
        setWindowStyles(alertDialogPane);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setGraphic(null);
        return alert.showAndWait();
    }

    private void disableOkButton(BookFx bookFx, Dialog<BookFx> dialog) {
        dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(bookFx.titleProperty().isEmpty()
                .or(bookFx.authorProperty().isEmpty()
                .or(bookFx.descriptionProperty().isEmpty()
                .or(bookFx.genreProperty().isEmpty()
                .or(bookFx.ISBNProperty().isEmpty()
                .or(bookFx.publisherProperty().isEmpty()))
                .or(bookFx.readDateProperty().isNull())))));
    }
    private void disableOkButton(GameFx gameFx, Dialog<GameFx> dialog){
        dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(gameFx.titleProperty().isEmpty()
                .or(gameFx.publisherProperty().isEmpty()
                .or(gameFx.genreProperty().isEmpty()
                .or(gameFx.descriptionProperty().isEmpty()
                .or(gameFx.platformProperty().isEmpty()
                .or(gameFx.modeProperty().isEmpty()
                .or(gameFx.releaseDateProperty().isNull())))))));
    }
    private void disableOkButton(MovieFx movieFx, Dialog<MovieFx> dialog){
        dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(movieFx.titleProperty().isEmpty()
                .or(movieFx.directorProperty().isEmpty()
                .or(movieFx.genreProperty().isEmpty()
                .or(movieFx.descriptionProperty().isEmpty()
                .or(movieFx.countryProperty().isEmpty()
                .or(movieFx.releaseDateProperty().isNull()))))));
    }
}
