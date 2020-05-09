module com.kodpalmowy {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens com.kodpalmowy.controllers to javafx.fxml;
    exports com.kodpalmowy.controllers;
    exports com.kodpalmowy to javafx.graphics;
    exports com.kodpalmowy.models;
//    exports com.kodpalmowy.database.dao;
    exports com.kodpalmowy.database.utils;
//    exports com.kodpalmowy.database.models;
}