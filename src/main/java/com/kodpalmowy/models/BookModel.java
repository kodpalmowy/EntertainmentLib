package com.kodpalmowy.models;

import com.kodpalmowy.database.dao.BookDao;
import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.utils.converters.BookConverter;

public class BookModel {

    public Book saveBookInDB(BookFx bookFx){
        Book book = BookConverter.convertToBook(bookFx);
        BookDao bookDao = new BookDao();
        bookDao.insertBook(book);
        return book;
    }
    public void updateBookInDB(BookFx bookFx){
        Book book = BookConverter.convertToBook(bookFx);
        BookDao bookDao = new BookDao();
        bookDao.updateBook(book);
    }

}
