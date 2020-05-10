package com.kodpalmowy.database.utils;

import com.kodpalmowy.models.Book;

import java.sql.*;

public class Helper {

    public Helper() {
    }

    public static void createTable() {
        String SQL_CREATE_TABLE_QUERY = "CREATE TABLE bookList (_id INT NOT NULL AUTO_INCREMENT, title VARCHAR(50) NOT NULL, author VARCHAR(50) NOT NULL, genre VARCHAR(50), description VARCHAR(200), " +
                "ISBN VARCHAR(20), publisher VARCHAR(50), rating INT(10), readDate DATETIME, PRIMARY KEY (_id)";
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement()) {
             statement.executeQuery(SQL_CREATE_TABLE_QUERY);
        } catch (SQLException sqle) {
             System.out.println("SQLException(CREATE TABLE) : " + sqle.getMessage());
             // Later change this to logger
        }
    }

    public static void insertBook(Book book) {
        String SQL_INSERT_QUERY = "INSERT INTO bookList (title, author, genre, description, ISBN, publisher, rating, readDate) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection connection = ConnectionClass.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY)) {
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getGenre());
            preparedStatement.setString(4,book.getDescription());
            preparedStatement.setString(5,book.getISBN());
            preparedStatement.setString(6,book.getPublisher());
            preparedStatement.setInt(7,book.getRating());
            preparedStatement.setDate(8, (Date) book.getReadDate());
            preparedStatement.executeQuery();
        } catch (SQLException sqle) {
             System.out.println("SQLException(INSERT) : " + sqle.getMessage());
            // Later change this to logger
        }
    }
}
