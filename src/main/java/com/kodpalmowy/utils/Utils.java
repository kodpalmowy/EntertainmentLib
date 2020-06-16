package com.kodpalmowy.utils;

import com.kodpalmowy.models.BaseFxModel;
import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.models.GameFx;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {
    public static Date convertToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static void copyBookFx(BookFx copied, BookFx copy) {
        copy.titleProperty().setValue(copied.getTitle());
        copy.authorProperty().setValue(copied.getAuthor());
        copy.genreProperty().setValue(copied.getGenre());
        copy.descriptionProperty().setValue(copied.getDescription());
        copy.publisherProperty().setValue(copied.getPublisher());
        copy.ratingProperty().setValue(copied.getRating());
        copy.readDateProperty().setValue(copied.getReadDate());
        copy.ISBNProperty().setValue(copied.getISBN());
    }
    public static void copyGameFx(GameFx copied, GameFx copy){
        copy.titleProperty().setValue(copied.getTitle());
        copy.publisherProperty().setValue(copied.getPublisher());
        copy.genreProperty().setValue(copied.getGenre());
        copy.descriptionProperty().setValue(copied.getDescription());
        copy.platformProperty().setValue(copied.getPlatform());
        copy.modeProperty().setValue(copied.getMode());
        copy.ratingProperty().setValue(copied.getRating());
        copy.releaseDateProperty().setValue(copied.getReleaseDate());
    }

    public static void disableButton(Button button, TableView<? extends BaseFxModel> tableView){
        button.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
    }
}
