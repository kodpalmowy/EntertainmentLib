package com.kodpalmowy.database.utils;

import java.sql.*;

public class Helper {

    private static Connection connection = ConnectionClass.getConnection();

    public Helper() {
    }

    public static void createTable(){
        String SQL_QUERY = "CREATE TABLE bookList (_id INT NOT NULL, title VARCHAR(50) NOT NULL, author VARCHAR(50) NOT NULL, genre VARCHAR(50), description VARCHAR(200), " +
                          "ISBN VARCHAR(20), publisher VARCHAR(50), rating INT(10), readDate DATETIME, PRIMARY KEY (_id)";
        try(Connection connection = ConnectionClass.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeQuery(SQL_QUERY);
        } catch (SQLException sqle){
            System.out.println("SQLException : " + sqle.getMessage());
        }
    }
}
