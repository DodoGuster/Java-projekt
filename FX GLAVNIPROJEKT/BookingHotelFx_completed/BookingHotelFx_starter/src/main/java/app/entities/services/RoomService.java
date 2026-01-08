package app.entities.services;

import app.entities.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomService {

    private final List<Room> rooms = new ArrayList<>();

    public void addHotel(Room r) {
        rooms.add(r);
    }

    public List<Room> getAll() {
        return rooms;
    }

    /**
     * Pronalazi sobu po broju sobe.
     * Vraća Optional<Room>.
     */
    public Optional<Room> findByNumber(int roomNumber) {
        return rooms.stream()
                .filter(r -> r.getNumber() == roomNumber)
                .findFirst();
    }
}
