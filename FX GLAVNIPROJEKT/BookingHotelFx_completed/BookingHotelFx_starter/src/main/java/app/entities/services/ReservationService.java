package app.entities.services;

import app.entities.Guest;
import app.entities.Reservation;
import app.entities.util.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static app.entities.Staff.log;

public class ReservationService {

    private final List<Reservation> reservations = new ArrayList<>();
    private static final File RES_FILE = new File("data/reservations.json");

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        saveToJsonQuietly();
    }

    public List<Reservation> getAll() {
        return reservations;
    }

    public Optional<Reservation> findByGuest(Guest guest) {
        if (guest == null) {
            return Optional.empty();
        }
        return reservations.stream()
                .filter(r -> r.getGuest() != null)
                .filter(r -> guest.getEmail() != null
                        && guest.getEmail().equalsIgnoreCase(r.getGuest().getEmail()))
                .findFirst();
    }


    public void replaceAll(List<Reservation> nove) {
        reservations.clear();
        reservations.addAll(nove);
        saveToJsonQuietly();
    }

    public void loadFromJson() {
        try {
            List<Reservation> ucitane = JsonUtils.readList(RES_FILE, Reservation[].class);
            reservations.clear();
            reservations.addAll(ucitane);
        } catch (IOException e) {
            log.info("Ne mogu učitati rezervacije iz JSON-a: {}", e.getMessage());
        }
    }

    private void saveToJsonQuietly() {
        try {
            RES_FILE.getParentFile().mkdirs();
            JsonUtils.writeList(RES_FILE, reservations);
        } catch (IOException e) {
            log.info("Ne mogu učitati rezervacije iz JSON-a: {}", e.getMessage());
        }
    }
}
