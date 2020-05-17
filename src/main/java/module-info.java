module com.kodpalmowy {
    requires javafx.controls;
    requires mysql.connector.java;
    requires java.sql;
    requires javafx.fxml;

    opens com.kodpalmowy.controllers to javafx.fxml;
    exports com.kodpalmowy.controllers;
    exports com.kodpalmowy to javafx.graphics;
    exports com.kodpalmowy.models;
    exports com.kodpalmowy.database.utils;
    exports com.kodpalmowy.utils;
    exports com.kodpalmowy.database.models;
}