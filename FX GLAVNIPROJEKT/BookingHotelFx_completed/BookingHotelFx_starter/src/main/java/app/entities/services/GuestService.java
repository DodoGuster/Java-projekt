package app.entities.services;

import app.entities.Guest;
import app.entities.util.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class GuestService {

    private final List<Guest> guests = new ArrayList<>();
    private static final File GUESTS_FILE = new File("data/guests.json");
    Logger log = Logger.getLogger(GuestService.class.getName());

    public void addGuest(Guest guest) {
        guests.add(guest);
        saveToJsonQuietly();
    }

    public List<Guest> getAll() {
        return guests;
    }

    public Optional<Guest> findByName(String name) {
        return guests.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name)
                        || g.getSurname().equalsIgnoreCase(name))
                .findFirst();
    }

    public void replaceAll(List<Guest> novi) {
        guests.clear();
        guests.addAll(novi);
        saveToJsonQuietly();
    }

    public void loadFromJson() {
        try {
            List<Guest> ucitani = JsonUtils.readList(GUESTS_FILE, Guest[].class);
            guests.clear();
            guests.addAll(ucitani);
        } catch (IOException e) {
           log.info("Ne mogu učitati goste iz JSON-a: " + e.getMessage());
        }
    }

    private void saveToJsonQuietly() {
        try {
            GUESTS_FILE.getParentFile().mkdirs();
            JsonUtils.writeList(GUESTS_FILE, guests);
        } catch (IOException e) {
            log.info("Ne mogu spremiti goste u JSON: " + e.getMessage());
        }
    }
}
