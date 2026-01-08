package app.entities;

public class InvalidHotelDataException extends RuntimeException {

    public InvalidHotelDataException(int hotelIndex, String hotelName, String address, String reason) {
        super("Neispravan unos za " + hotelIndex + ". hotel" +
                " (ime: '" + hotelName + "', adresa: '" + address + "') – " + reason);
    }
}
