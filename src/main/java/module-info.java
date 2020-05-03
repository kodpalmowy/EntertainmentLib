module com.kodpalmowy {
    requires javafx.controls;
    requires javafx.fxml;

    opens fxml to javafx.fxml;
    opens com.kodpalmowy.controllers to javafx.fxml;
    exports com.kodpalmowy.controllers;
    exports com.kodpalmowy to javafx.graphics;
}