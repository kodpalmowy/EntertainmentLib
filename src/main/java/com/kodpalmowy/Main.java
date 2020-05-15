package com.kodpalmowy;

import com.kodpalmowy.database.utils.ConnectionClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("en"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.language");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainBorderPane.fxml"),resourceBundle);
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(resourceBundle.getString("title.app"));
        stage.show();
        stage.setResizable(false);
        ConnectionClass.initializeDB();
    }

    public static void main(String[] args) {
        launch();
    }
}