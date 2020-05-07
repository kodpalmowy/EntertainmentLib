package com.kodpalmowy.connectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    private static final String dbName = "jdbc:mysql://localhost:3306/ELdb";
    private static final String userName = "root";
    private static final String userPassword = "kodpalmowy";
    static Connection connection;


    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(dbName, userName, userPassword);
        } catch (SQLException sqle) {
            System.out.println("SQLException error : " + sqle.getMessage());
        }
        return connection;

    }
}
