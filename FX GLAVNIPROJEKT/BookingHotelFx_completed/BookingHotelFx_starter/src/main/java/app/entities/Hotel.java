package app.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

/**
 * Radenje hotela sa sobama i osobljem, koristeći kolekcije (Map, Set)
 *
 * @see Cancelable
 */
public final class Hotel implements Cancelable {
    private static final Logger log = LoggerFactory.getLogger(Hotel.class);

    private String name;             // obavezno
    private City city;               // obavezno
    private String address;          // obavezno
    private Map<Integer, Room> rooms = new HashMap<>();
    private Set<Staff> staff = new HashSet<>();
    private boolean cancellable;     // opcionalno
    private HotelType type = HotelType.BUSINESS;          // enumeracija (npr. LUXURY, BUSINESS...)

    /** Default konstruktor za JSON-B. */
    public Hotel() {
    }

    private Hotel(Builder builder) {
        log.trace("Ulazak u builder.");
        this.name = builder.name;
        this.city = builder.city;
        this.address = builder.address;
        this.rooms = builder.rooms;
        this.staff = builder.staff;
        this.cancellable = builder.cancellable;
        this.type = builder.type;
    }

    /** Enumeracija za tip hotela */
    public enum HotelType {
        LUXURY, BUSINESS, BOUTIQUE, FAMILY, HOTEL, APARTMENT, BUDGET
    }

    /** Builder klasa */
    public static class Builder {
        // obavezna polja
        private final String name;
        private final City city;
        private final String address;

        // opcionalna polja (kolekcije)
        private Map<Integer, Room> rooms = new HashMap<>();
        private Set<Staff> staff = new HashSet<>();
        private boolean cancellable;
        private HotelType type = HotelType.BUSINESS;

        public Builder(String name, City city, String address) {
            this.name = name;
            this.city = city;
            this.address = address;
            log.info("Pokrenut unos obaveznih parametara hotela.");
        }

        public Builder setRooms(Map<Integer, Room> rooms) {
            this.rooms = rooms;
            return this;
        }

        public Builder setStaff(Set<Staff> staff) {
            this.staff = staff;
            return this;
        }

        public Builder setType(HotelType type) {
            this.type = type;
            return this;
        }

        public Builder setCancellable(boolean cancellable) {
            this.cancellable = cancellable;
            return this;
        }

        public Hotel build() {
            log.info("Napravljen je build hotela!");
            return new Hotel(this);
        }
    }

    @Override
    public boolean canBeCancelled() {
        log.info("Provjera može li se otkazati hotel!");
        return cancellable;
    }

    // Getteri
    public String getName() { return name; }
    public City getCity() { return city; }
    public String getAddress() { return address; }
    public Map<Integer, Room> getRooms() { return Collections.unmodifiableMap(rooms); }
    public Set<Staff> getStaff() { return Collections.unmodifiableSet(staff); }
    public boolean isCancellable() { return cancellable; }
    public HotelType getType() { return type; }

    // Setteri za JSON-B
    public void setName(String name) { this.name = name; }
    public void setCity(City city) { this.city = city; }
    public void setAddress(String address) { this.address = address; }
    public void setRooms(Map<Integer, Room> rooms) { this.rooms = rooms == null ? new HashMap<>() : rooms; }
    public void setStaff(Set<Staff> staff) { this.staff = staff == null ? new HashSet<>() : staff; }
    public void setCancellable(boolean cancellable) { this.cancellable = cancellable; }
    public void setType(HotelType type) { this.type = type; }

    // Pomoćne metode
    public void addRoom(Room room) {
        rooms.put(room.getNumber(), room);
        log.info("Dodana soba broj: {}", room.getNumber());
    }

    public Room getRoomByNumber(int number) {
        return rooms.get(number);
    }

    public void addStaffMember(Staff person) {
        staff.add(person);
        log.info("Dodano osoblje: {}", person.getName());
    }
}
