package app.entities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Unosenje soba za hotel
 *
 * @see Hotel
 */

public class Room {
    private static final Logger log = LoggerFactory.getLogger(Room.class);

    private int roomNumber;
    private BigDecimal pricePerNight;
    private String description;   // npr. "Soba za jednu osobu"
    private int numberOfBeds;

    /** Default konstruktor za JSON-B. */
    public Room() {
    }

    public Room(int number, BigDecimal pricePerNight,  String description, int numberOfBeds) {
        this.roomNumber = number;
        this.pricePerNight = pricePerNight;
        this.description = description;
        this.numberOfBeds = numberOfBeds;

        log.info("Soba kreirana sa parametrima: {} {} {} {} !", number, pricePerNight, description, numberOfBeds);
    }

    // kompatibilnost s postojećim RoomService kodom
    public int getNumber() {
        return roomNumber;
    }
    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getNumberOfBeds() {
        return numberOfBeds;
    }
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

}
