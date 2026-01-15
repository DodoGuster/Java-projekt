package com.dominik.controller;

import app.entities.Reservation;
import com.dominik.service.ReservationServiceFx;
import com.dominik.util.DialogUtil;
import com.dominik.util.ValidationUtil;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class ReservationsController {

    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    @FXML private TextField hotelField;
    @FXML private TextField guestEmailField;
    @FXML private DatePicker fromDate;
    @FXML private DatePicker toDate;

    @FXML private TableView<Reservation> table;
    @FXML private TableColumn<Reservation, String> hotelCol;
    @FXML private TableColumn<Reservation, Integer> roomCol;
    @FXML private TableColumn<Reservation, String> guestCol;
    @FXML private TableColumn<Reservation, LocalDate> startCol;
    @FXML private TableColumn<Reservation, LocalDate> endCol;

    private final ObservableList<Reservation> data = FXCollections.observableArrayList();
    private final ReservationServiceFx service = new ReservationServiceFx();

    @FXML
    public void initialize() {
        hotelCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(cd.getValue().getHotelNameSafe()));
        roomCol.setCellValueFactory(cd -> new ReadOnlyObjectWrapper<>(cd.getValue().getRoomNumberSafe()));
        guestCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(cd.getValue().getGuestFullNameSafe()));
        startCol.setCellValueFactory(cd -> new ReadOnlyObjectWrapper<>(cd.getValue().getReservationStart()));
        endCol.setCellValueFactory(cd -> new ReadOnlyObjectWrapper<>(cd.getValue().getReservationEnd()));

        table.setItems(data);
        data.setAll(service.findAll());
    }

    @FXML
    private void onSearch() {
        try {
            String email = guestEmailField.getText();
            if (!ValidationUtil.isBlank(email) && !EMAIL.matcher(email.trim()).matches()) {
                throw new IllegalArgumentException("Email gosta nije ispravnog formata.");
            }

            LocalDate from = fromDate.getValue();
            LocalDate to = toDate.getValue();
            if (from != null && to != null && from.isAfter(to)) {
                throw new IllegalArgumentException("Datum OD ne smije biti nakon datuma DO.");
            }

            List<Reservation> result = service.search(
                    hotelField.getText(),
                    email,
                    from,
                    to
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
        hotelField.clear();
        guestEmailField.clear();
        fromDate.setValue(null);
        toDate.setValue(null);
        data.setAll(service.findAll());
    }
}
