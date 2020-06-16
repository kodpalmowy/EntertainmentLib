package com.kodpalmowy.database.dataAccessObject;

import com.kodpalmowy.database.utils.ConnectionClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao {
    private static void createBookTable() {
        String SQL_CREATE_BOOKS_TABLE_QUERY = "CREATE TABLE bookList (bookId INT NOT NULL AUTO_INCREMENT, " +
                "title VARCHAR(200) NOT NULL, " +
                "author VARCHAR(200) NOT NULL, " +
                "genre VARCHAR(45) NOT NULL, " +
                "description VARCHAR(300) NOT NULL, " +
                "ISBN VARCHAR(25) NOT NULL, " +
                "publisher VARCHAR(100) NOT NULL, " +
                "rating INT NOT NULL, " +
                "readDate DATETIME NOT NULL, " +
                "PRIMARY KEY (bookId)";
        tableUtil(SQL_CREATE_BOOKS_TABLE_QUERY);
    }

    private static void createMovieTable() {
        String SQL_CREATE_MOVIES_TABLE_QUERY = "CREATE TABLE movieList (`movieId` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `title` VARCHAR(200) NOT NULL,\n" +
                "  `director` VARCHAR(200) NOT NULL,\n" +
                "  `genre` VARCHAR(45) NOT NULL,\n" +
                "  `description` VARCHAR(300) NOT NULL,\n" +
                "  `rating` INT NOT NULL,\n" +
                "  `country` VARCHAR(100) NOT NULL, \n" +
                "  `releaseDate` DATETIME NOT NULL,\n" +
                "  PRIMARY KEY (`movieId`));";
        tableUtil(SQL_CREATE_MOVIES_TABLE_QUERY);
    }

    private static void createGameTable() {
        String SQL_CREATE_GAMES_TABLE_QUERY = "CREATE TABLE gameList (`gameId` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `title` VARCHAR(200) NOT NULL,\n" +
                "  `publisher` VARCHAR(200) NOT NULL,\n" +
                "  `genre` VARCHAR(45) NOT NULL,\n" +
                "  `description` VARCHAR(300) NOT NULL,\n" +
                "  `platform` VARCHAR(45) NOT NULL,\n" +
                "  `mode` VARCHAR(45) NOT NULL,\n" +
                "  `rating` INT NOT NULL,\n" +
                "  `releaseDate` DATETIME NOT NULL,\n" +
                "  PRIMARY KEY (`gameId`));";
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
