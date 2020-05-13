package com.kodpalmowy.database.dao;

import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.database.utils.ConnectionClass;

import java.sql.*;

public class BookDao extends Dao{
    public void insertBook(Book book){
        String SQL_INSERT_QUERY = "INSERT INTO bookList (title, author, genre, description, ISBN, publisher, rating, readDate) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection connection = ConnectionClass.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setString(5, book.getISBN());
            preparedStatement.setString(6, book.getPublisher());
            preparedStatement.setInt(7, book.getRating());
            preparedStatement.setDate(8, (Date) book.getReadDate());
            preparedStatement.execute();
        } catch (SQLException sqle) {
            System.out.println("SQLException(INSERT) : " + sqle.getMessage());
            // change this later to logger
        }
    }
    public void deleteBook(Integer id){
        String SQL_DELETE_QUERY = "DELETE FROM bookList WHERE _ID=" + id;
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeQuery(SQL_DELETE_QUERY);
        } catch (SQLException sqle) {
            System.out.println("SQLException(INSERT) : " + sqle.getMessage());
            // change this later to logger
        }
    }


}
