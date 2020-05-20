package com.kodpalmowy.utils;

import com.kodpalmowy.controllers.BookController;
import com.kodpalmowy.controllers.BookLibController;
import com.kodpalmowy.models.BookFx;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DialogUtils {

    private BookFx temporaryBookFx = new BookFx();

    public boolean showDialog(BookFx bookFx, String dialogTitle, BookLibController.DialogMode mode, ObservableList<BookFx> obList){

        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.language");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/bookDialog.fxml"));
            loader.setResources(resourceBundle);
            DialogPane addBookDialog = loader.load();

            BookController bookController = loader.getController();
            bookController.setDefaultValues();

            copyBookFx(bookFx, temporaryBookFx);
            bookController.setBook(temporaryBookFx);

            Dialog<BookFx> dialog = new Dialog<>();
            dialog.setDialogPane(addBookDialog);
            dialog.setTitle(dialogTitle);
            disableOkButton(temporaryBookFx, dialog);

            dialog.setResultConverter(button -> button == ButtonType.OK ? temporaryBookFx : null);
            dialog.showAndWait().ifPresent(result -> {
                copyBookFx(result,bookFx);
                if (mode == BookLibController.DialogMode.ADD){
                    bookController.bookModel.saveBookInDB(bookFx);
                    obList.add(bookFx);
                } else if (mode == BookLibController.DialogMode.EDIT){
                    bookController.bookModel.updateBookInDB(bookFx);
                }
            });
        } catch (IOException ioe){
            System.out.println("Exception (showDialog) : " + ioe.getMessage());
            ioe.printStackTrace();
        }
        return false;
    }

    private void copyBookFx(BookFx copied, BookFx copy){
        copy.titleProperty().setValue(copied.getTitle());
        copy.authorProperty().setValue(copied.getAuthor());
        copy.genreProperty().setValue(copied.getGenre());
        copy.descriptionProperty().setValue(copied.getDescription());
        copy.publisherProperty().setValue(copied.getPublisher());
        copy.ratingProperty().setValue(copied.getRating());
        copy.readDateProperty().setValue(copied.getReadDate());
        copy.ISBNProperty().setValue(copied.getISBN());
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

    public Optional<ButtonType> showAlertDialog(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setGraphic(null);
        return alert.showAndWait();
    }

}
