package app.entities.services;

import app.entities.Hotel;
import app.entities.util.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class HotelService {

    private final List<Hotel> hotels = new ArrayList<>();

    private static final File HOTELS_FILE = new File("data/hotels.json");
    Logger log = Logger.getLogger(HotelService.class.getName());

    public void addHotel(Hotel h) {
        hotels.add(h);
        saveToJsonQuietly();
    }

    public List<Hotel> getAll() {
        return hotels;
    }

    public Optional<Hotel> findByName(String name) {
        return hotels.stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public void replaceAll(List<Hotel> novi) {
        hotels.clear();
        hotels.addAll(novi);
        saveToJsonQuietly();
    }

    public void loadFromJson() {
        try {
            List<Hotel> ucitani = JsonUtils.readList(HOTELS_FILE, Hotel[].class);
            hotels.clear();
            hotels.addAll(ucitani);
        } catch (IOException e) {
            log.info("Ne mogu učitati hotele iz JSON-a: " + e.getMessage());
        }
    }

    public void saveToJsonQuietly() {
        try {
            HOTELS_FILE.getParentFile().mkdirs();
            JsonUtils.writeList(HOTELS_FILE, hotels);
        } catch (IOException e) {
            log.info("Ne mogu spremiti hotele u JSON: " + e.getMessage());
        }
    }
}
