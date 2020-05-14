package com.kodpalmowy.database.dao;

import com.kodpalmowy.database.utils.ConnectionClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao {
    private static void createBookTable() {
        String SQL_CREATE_BOOKS_TABLE_QUERY = "CREATE TABLE bookList (bookId INT NOT NULL AUTO_INCREMENT, title VARCHAR(100) NOT NULL, author VARCHAR(100) NOT NULL, genre VARCHAR(50), description VARCHAR(300), " +
                "ISBN VARCHAR(25), publisher VARCHAR(100), rating INT(10), readDate DATETIME, PRIMARY KEY (bookId)";
        tableUtil(SQL_CREATE_BOOKS_TABLE_QUERY);
    }

    private static void createMovieTable() {
        String SQL_CREATE_MOVIES_TABLE_QUERY = "CREATE TABLE movieList (_ID INT NOT NULL AUTO_INCREMENT, title VARCHAR(100) NOT NULL, genre VARCHAR(50), description VARCHAR(300), " +
                "rating INT(10), watchDate DATETIME, PRIMARY KEY (_id)";
        tableUtil(SQL_CREATE_MOVIES_TABLE_QUERY);
    }

    private static void createGameTable() {
        String SQL_CREATE_GAMES_TABLE_QUERY = "CREATE TABLE gamesList (_ID INT NOT NULL AUTO_INCREMENT, title VARCHAR(100) NOT NULL, genre VARCHAR(50), description VARCHAR(300), " +
                "publisher VARCHAR(100), rating INT(10), PRIMARY KEY (_id)";
        tableUtil(SQL_CREATE_GAMES_TABLE_QUERY);
    }

    public static void createAllTables(){
        createBookTable();
        createGameTable();
        createMovieTable();
    }

    private static void tableUtil(String SQL_CREATE_QUERY) {
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(SQL_CREATE_QUERY);
        } catch (SQLException sqle) {
            System.out.println("SQLException(CREATE TABLE) : " + sqle.getMessage());
            // Later change this to logger
        }
    }
}
