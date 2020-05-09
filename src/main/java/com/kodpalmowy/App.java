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
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("en"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.language");
        stage.setTitle(resourceBundle.getString("title.app"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainBorderPane.fxml"),resourceBundle);
        Parent parent = fxmlLoader.load();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        ConnectionClass.initializeDB();
    }

    public static void main(String[] args) {
        launch();
    }

}