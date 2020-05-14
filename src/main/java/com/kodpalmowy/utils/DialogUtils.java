package com.kodpalmowy.utils;

import com.kodpalmowy.controllers.AddBookController;
import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.utils.converters.BookConverter;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DialogUtils {

    public BookFx showDialog(){
        BookFx bookFx = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/addBookDialog.fxml"));
            DialogPane addBookDialog = loader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(addBookDialog);
            dialog.setTitle("Add book");
            AddBookController addBookController = loader.getController();
            addBookController.setDefaultValues();
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == ButtonType.OK){
                Book book = addBookController.bookModel.saveBookInDB();
                bookFx = BookConverter.convertToBookFx(book);
            }
        } catch (IOException | NoSuchElementException ioe){
            System.out.println("Exception (ADD BOOK) : " + ioe.getMessage());
            ioe.printStackTrace();
        }
        return bookFx;
    }
    public Optional<ButtonType> showAlertDialog(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setGraphic(null);
        Optional<ButtonType> alertResult = alert.showAndWait();
        return alertResult;
    }

}
