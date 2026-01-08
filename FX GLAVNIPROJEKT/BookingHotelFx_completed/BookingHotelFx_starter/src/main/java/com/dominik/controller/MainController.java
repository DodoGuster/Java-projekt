package com.dominik.controller;

import com.dominik.util.DialogUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.util.Objects;

public class MainController {

    @FXML
    private BorderPane root;

    @FXML
    private void openHotels() {
        loadToCenter("/view/hotels.fxml");
    }

    @FXML
    private void openGuests() {
        loadToCenter("/view/guests.fxml");
    }

    @FXML
    private void openReservations() {
        loadToCenter("/view/reservations.fxml");
    }

    private void loadToCenter(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            root.setCenter(view);
        } catch (Exception ex) {
            DialogUtil.error("Greška", "Ne mogu učitati ekran: " + fxmlPath + "\n" + ex.getMessage());
        }
    }
}
