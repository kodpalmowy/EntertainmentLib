package com.kodpalmowy.utils;

import com.kodpalmowy.controllers.BookController;
import com.kodpalmowy.controllers.BookLibController;
import com.kodpalmowy.models.BookFx;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DialogUtils {

    private BookFx temporaryBookFx = null;

    public Boolean showDialog(final BookFx bookFx, String dialogTitle, BookLibController.DialogMode mode){
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.language");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/bookDialog.fxml"));
            loader.setResources(resourceBundle);
            DialogPane addBookDialog = loader.load();

            BookController bookController = loader.getController();
            bookController.setDefaultValues();

            copyBookFx(bookFx);
            bookController.setBook(temporaryBookFx);

            Dialog<BookFx> dialog = new Dialog<>();
            dialog.setDialogPane(addBookDialog);
            dialog.setTitle(dialogTitle);
            disableOkButton(bookFx, dialog);

            dialog.setResultConverter(button -> button == ButtonType.OK ? temporaryBookFx : bookFx);
            dialog.showAndWait().ifPresent(book -> {
                if (mode == BookLibController.DialogMode.ADD){
                    bookController.bookModel.saveBookInDB(book);
                } else if (mode == BookLibController.DialogMode.EDIT){
                    bookController.bookModel.updateBookInDB(book);
                }
            });
//            if (result.get() == ButtonType.OK){
//                if (mode == BookLibController.DialogMode.ADD){
//                    bookController.bookModel.saveBookInDB(bookFx);
//                    return true;
//                } else if (mode == BookLibController.DialogMode.EDIT){
//                    bookController.bookModel.updateBookInDB(bookFx);
//                    return true;
//                }
//            }
        } catch (IOException | NoSuchElementException ioe){
            System.out.println("Exception (showDialog) : " + ioe.getMessage());
            ioe.printStackTrace();
        }
        return false;
    }

    private void copyBookFx(BookFx bookFx){
        this.temporaryBookFx = bookFx;
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
