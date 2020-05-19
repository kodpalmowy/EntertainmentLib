package com.kodpalmowy.database.utils;

import com.kodpalmowy.database.dataAccessObject.Dao;

import java.sql.*;

public class ConnectionClass {

    private static final String dbName = "jdbc:mysql://localhost:3306/ELdb";
    private static final String userName = "root";
    private static final String userPassword = "kodpalmowy";
    private static Connection connection;

    public static void initializeDB() {
        createConnection();
        createTable();
        closeConnection();
    }

    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException (CLOSE CONNECTION) : " + sqle.getMessage());
                // Later change this to logger
            }
        }
    }

    private static void createTable() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tablesInDB = metaData.getTables(null, null, "bookList", null);
            if (!tablesInDB.next()) {
                Dao.createAllTables();
            }
            tablesInDB.close();
        } catch (SQLException slqe) {
            System.out.println("SQLException (CREATE TABLE) : " + slqe.getMessage());
            // Later change this to logger
        }
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(dbName, userName, userPassword);
        } catch (SQLException sqle) {
            System.out.println("SQLException error (CREATE CONNECTION) : " + sqle.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                createConnection();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return connection;
        }
    }
}
