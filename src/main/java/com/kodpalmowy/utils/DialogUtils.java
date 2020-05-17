package com.kodpalmowy.utils;

import com.kodpalmowy.controllers.BookController;
import com.kodpalmowy.controllers.BookLibController;
import com.kodpalmowy.models.BookFx;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DialogUtils {

    public Boolean showDialog(BookFx bookFx, String dialogTitle, BookLibController.DialogMode mode, ObservableList<BookFx> list){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/bookDialog.fxml"));
            DialogPane addBookDialog = loader.load();

            BookController bookController = loader.getController();
            bookController.setDefaultValues();
            bookController.setBook(bookFx);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(addBookDialog);
            dialog.setTitle(dialogTitle);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.orElse(null) == ButtonType.OK){
                if (mode == BookLibController.DialogMode.ADD){
                    bookController.bookModel.saveBookInDB(bookFx);
                    return true;
                } else if (mode == BookLibController.DialogMode.EDIT){
                    bookController.bookModel.updateBookInDB(bookFx);
                    return true;
                }
            }
        } catch (IOException | NoSuchElementException ioe){
            System.out.println("Exception (ADD BOOK) : " + ioe.getMessage());
            ioe.printStackTrace();
        }
        return false;
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
