package com.dominik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getResource("/view/main.fxml")
        ));

        stage.setTitle("BookingHotelFx");
        stage.setScene(new Scene(root, 1000, 650));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
