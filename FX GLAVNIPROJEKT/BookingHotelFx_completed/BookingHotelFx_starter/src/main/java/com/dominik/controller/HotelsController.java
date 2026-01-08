package com.dominik.controller;

import app.entities.Hotel;
import app.entities.City;
import com.dominik.service.HotelServiceFx;
import com.dominik.util.DialogUtil;
import com.dominik.util.ValidationUtil;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class HotelsController {

    @FXML private TextField nameField;
    @FXML private TextField cityField;
    @FXML private TextField typeField;

    @FXML private TableView<Hotel> table;
    @FXML private TableColumn<Hotel, String> nameCol;
    @FXML private TableColumn<Hotel, String> cityCol;
    @FXML private TableColumn<Hotel, String> typeCol;

    private final ObservableList<Hotel> data = FXCollections.observableArrayList();
    private final HotelServiceFx service = new HotelServiceFx();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(cd.getValue().getName()));
        cityCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(String.valueOf(cd.getValue().getCity())));
        typeCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(cd.getValue().getType().name()));

        table.setItems(data);
        data.setAll(service.findAll());
    }

    @FXML
    private void onSearch() {
        try {
            validateCity(cityField.getText());
            validateHotelType(typeField.getText());

            List<Hotel> result = service.search(
                    nameField.getText(),
                    cityField.getText(),
                    typeField.getText()
            );

            if (result.isEmpty()) {
                DialogUtil.info("Rezultat", "Nema rezultata za zadane kriterije.");
            }

            data.setAll(result);
        } catch (IllegalArgumentException ex) {
            DialogUtil.error("Neispravan unos", ex.getMessage());
        }
    }

    private void validateCity(String cityText) {
        if (ValidationUtil.isBlank(cityText)) {
            return;
        }
        try {
            City.valueOf(cityText.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Grad mora biti jedan od: ZAGREB ili OSIJEK.");
        }
    }

    private void validateHotelType(String typeText) {
        if (ValidationUtil.isBlank(typeText)) {
            return;
        }
        try {
            Hotel.HotelType.valueOf(typeText.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Tip hotela nije ispravan (npr. LUXURY, BUSINESS, BOUTIQUE...).");
        }
    }

    @FXML
    private void onReset() {
        nameField.clear();
        cityField.clear();
        typeField.clear();
        data.setAll(service.findAll());
    }
}
