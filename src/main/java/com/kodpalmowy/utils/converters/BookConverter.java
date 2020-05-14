package com.kodpalmowy.utils.converters;

import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.models.BookFx;
import com.kodpalmowy.utils.Utils;

public class BookConverter {
    public static Book convertToBook(BookFx bookFx){
        Book book = new Book();
        book.setBookId(bookFx.getBookId());
        book.setTitle(bookFx.getTitle());
        book.setAuthor(bookFx.getAuthor());
        book.setDescription(bookFx.getDescription());
        book.setGenre(bookFx.getGenre());
        book.setISBN(bookFx.getISBN());
        book.setRating(bookFx.getRating());
        book.setPublisher(bookFx.getPublisher());
        book.setReadDate(Utils.convertToDate(bookFx.getReadDate()));
        return book;
    }

    public static BookFx convertToBookFx(Book book){
        BookFx bookFx = new BookFx();
        bookFx.setBookId(book.getBookId());
        bookFx.setTitle(book.getTitle());
        bookFx.setAuthor(book.getAuthor());
        bookFx.setDescription(book.getDescription());
        bookFx.setGenre(book.getGenre());
        bookFx.setISBN(book.getISBN());
        bookFx.setRating(book.getRating());
        bookFx.setPublisher(book.getPublisher());
        bookFx.setReadDate(Utils.convertToLocalDate(book.getReadDate()));
        return bookFx;
    }
}
