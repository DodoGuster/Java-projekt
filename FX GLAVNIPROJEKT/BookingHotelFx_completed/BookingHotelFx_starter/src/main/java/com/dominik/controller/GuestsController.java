package com.dominik.controller;

import app.entities.Guest;
import com.dominik.service.GuestServiceFx;
import com.dominik.util.DialogUtil;
import com.dominik.util.ValidationUtil;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.regex.Pattern;

public class GuestsController {

    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField emailField;
    @FXML private TextField minAgeField;
    @FXML private TextField maxAgeField;

    @FXML private TableView<Guest> table;
    @FXML private TableColumn<Guest, String> nameCol;
    @FXML private TableColumn<Guest, String> surnameCol;
    @FXML private TableColumn<Guest, String> emailCol;
    @FXML private TableColumn<Guest, Integer> ageCol;

    private final ObservableList<Guest> data = FXCollections.observableArrayList();
    private final GuestServiceFx service = new GuestServiceFx();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(cd.getValue().getName()));
        surnameCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(cd.getValue().getSurname()));
        emailCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(cd.getValue().getEmail()));
        ageCol.setCellValueFactory(cd -> new ReadOnlyObjectWrapper<>(
                cd.getValue().getAge() == null ? null : cd.getValue().getAge().intValue()
        ));

        table.setItems(data);
        data.setAll(service.findAll());
    }

    @FXML
    private void onSearch() {
        try {
            String email = emailField.getText();
            if (!ValidationUtil.isBlank(email) && !EMAIL.matcher(email.trim()).matches()) {
                throw new IllegalArgumentException("Email nije ispravnog formata.");
            }

            Integer minAge = ValidationUtil.parseNullablePositiveInt(minAgeField.getText(),
                    "Minimalna dob mora biti pozitivan cijeli broj.");
            Integer maxAge = ValidationUtil.parseNullablePositiveInt(maxAgeField.getText(),
                    "Maksimalna dob mora biti pozitivan cijeli broj.");

            if (minAge != null && maxAge != null && minAge > maxAge) {
                throw new IllegalArgumentException("Minimalna dob ne smije biti veća od maksimalne.");
            }

            List<Guest> result = service.search(
                    nameField.getText(),
                    surnameField.getText(),
                    email,
                    minAge,
                    maxAge
            );

            if (result.isEmpty()) {
                DialogUtil.info("Rezultat", "Nema rezultata za zadane kriterije.");
            }
            data.setAll(result);

        } catch (IllegalArgumentException ex) {
            DialogUtil.error("Neispravan unos", ex.getMessage());
        }
    }

    @FXML
    private void onReset() {
        nameField.clear();
        surnameField.clear();
        emailField.clear();
        minAgeField.clear();
        maxAgeField.clear();
        data.setAll(service.findAll());
    }
}
