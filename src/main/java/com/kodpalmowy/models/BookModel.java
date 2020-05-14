package com.kodpalmowy.models;

import com.kodpalmowy.database.dao.BookDao;
import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.utils.converters.BookConverter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class BookModel {

    private final ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>(new BookFx());

    public Book saveBookInDB(){
        Book book = BookConverter.convertToBook(this.getBookFxObjectProperty());
        BookDao bookDao = new BookDao();
        bookDao.insertBook(book);
        return book;
    }

    public BookFx getBookFxObjectProperty() {
        return bookFxObjectProperty.get();
    }

}
