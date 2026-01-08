package app.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Radenje rezervacije od strane korisnika
 *
 * Unosi koji hotel gost zeli, koju sobu i datum za rezervaciju
 *
 *
 */

public class Reservation{
    private static final Logger log = LoggerFactory.getLogger(Reservation.class);

    private Hotel hotel;
    private Room room;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private Guest guest;

    /**
     * Default konstruktor za JSON-B.
     */
    public Reservation() {
    }

    public Reservation(Hotel hotel, Room room, LocalDate reservationStart, LocalDate reservationEnd, Guest guest) {
        this.hotel = hotel;
        this.room = room;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
        this.guest = guest;

        log.info("Reservation created, {} {} {} {} {}",hotel,room,reservationStart,reservationEnd,guest);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public String getHotelNameSafe() {
        return hotel == null ? "" : hotel.getName();
    }

    public String getGuestEmailSafe() {
        return guest == null ? "" : guest.getEmail();
    }

    public String getGuestFullNameSafe() {
        if (guest == null) {
            return "";
        }
        return guest.getName() + " " + guest.getSurname();
    }

    public Integer getRoomNumberSafe() {
        if (room == null) {
            return null;
        }
        return room.getNumber();
    }
    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public LocalDate getReservationStart() {
        return reservationStart;
    }
    public void setReservationStart(LocalDate reservationStart) {
        this.reservationStart = reservationStart;
    }
    public LocalDate getReservationEnd() {
        return reservationEnd;
    }
    public void setReservationEnd(LocalDate reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

    public static String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        log.trace("Pokrenut formator datuma!");
        return dateTime.format(formatter);
    }

}
