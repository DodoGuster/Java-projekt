package app.entities.backup;

import app.entities.Hotel;
import app.entities.Guest;
import app.entities.Reservation;

import java.io.Serializable;
import java.util.List;

public class BackupData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Hotel> hotels;
    private final List<Guest> guests;
    private final List<Reservation> reservations;

    public BackupData(List<Hotel> hotels,
                      List<Guest> guests,
                      List<Reservation> reservations) {
        this.hotels = hotels;
        this.guests = guests;
        this.reservations = reservations;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
