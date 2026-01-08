package com.dominik.service;

import app.entities.Reservation;
import app.entities.services.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class ReservationServiceFx {

    private final ReservationService reservationService = AppContext.reservations();

    public List<Reservation> findAll() {
        return reservationService.getAll();
    }

    public List<Reservation> search(String hotelName, String guestEmail,
                                    LocalDate dateFrom, LocalDate dateTo) {
        return findAll().stream()
                .filter(r -> containsIgnoreCase(r.getHotelNameSafe(), hotelName))
                .filter(r -> containsIgnoreCase(r.getGuestEmailSafe(), guestEmail))
                .filter(r -> overlaps(r.getReservationStart(), r.getReservationEnd(), dateFrom, dateTo))
                .toList();
    }

    private boolean overlaps(LocalDate start, LocalDate end,
                             LocalDate queryFrom, LocalDate queryTo) {
        if (start == null || end == null) {
            return false;
        }
        if (queryFrom == null && queryTo == null) {
            return true;
        }
        LocalDate from = queryFrom == null ? start : queryFrom;
        LocalDate to = queryTo == null ? end : queryTo;
        return !(end.isBefore(from) || start.isAfter(to));
    }

    private boolean containsIgnoreCase(String value, String query) {
        if (query == null || query.trim().isEmpty()) return true;
        if (value == null) return false;

        String v = value.toLowerCase(Locale.ROOT);
        String q = query.trim().toLowerCase(Locale.ROOT);
        return v.contains(q);
    }
}
