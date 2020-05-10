package com.kodpalmowy.database.utils;

import java.sql.*;

public class ConnectionClass {

    private static final String dbName = "jdbc:mysql://localhost:3306/ELdb";
    private static final String userName = "root";
    private static final String userPassword = "kodpalmowy";
    private static Connection connection;

    public static void initializeDB() {
        createConnection();
        createTable();
        
    }

    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException : " + sqle.getMessage());
                // Later change this to logger
            }
        }
    }

    private static void createTable() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tablesInDB = metaData.getTables(null, null, "bookList", null);
            if (!tablesInDB.next()) {
                Helper.createTable();
            }
            tablesInDB.close();
        } catch (SQLException slqe) {
            System.out.println("SQLException : " + slqe.getMessage());
            // Later change this to logger
        }
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(dbName, userName, userPassword);
        } catch (SQLException sqle) {
            System.out.println("SQLException error : " + sqle.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }
}
