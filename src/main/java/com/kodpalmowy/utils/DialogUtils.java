package com.kodpalmowy.utils;

import com.kodpalmowy.controllers.AddBookController;
import com.kodpalmowy.database.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class DialogUtils {

    private final ObservableList<String> bookGenres = FXCollections.observableArrayList("Drama","Fairytale","Poetry","Satire","Review","Religion","Autobiography","Diary",
            "True Crime","Fantasy","Adventure","Romance","Contemporary","Dystopian","Mystery",
            "Horror","Thriller","Paranormal","Historical fiction", "Science fiction","Memoir",
            "Cooking","Art","Self-help","Development","Motivational","Health","History","Travel",
            "Guide","Humor","Children").sorted();
    private final ObservableList<Integer> bookRatings = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);

    public enum DIALOG_MODE {ADD, EDIT}

    public Book showDialog(DIALOG_MODE mode){
        Book book = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/addBookDialog.fxml"));
            DialogPane addBookDialog = loader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(addBookDialog);
            dialog.setTitle("Add book");
            AddBookController addBookController = loader.getController();
            addBookController.genrePick.setItems(bookGenres);
            addBookController.ratingPick.setItems(bookRatings);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == ButtonType.OK){
                book = addBookController.processAddBook();
            }
        } catch (IOException | NoSuchElementException ioe){
            System.out.println("Exception (ADD BOOK) : " + ioe.getMessage());
            ioe.printStackTrace();
        }
        return book;
    }

}
