package com.dominik.service;

import app.entities.services.GuestService;
import app.entities.services.HotelService;
import app.entities.services.ReservationService;

/**
 * Jedno mjesto gdje držimo servise i učitavanje JSON podataka.
 *
 * Kontroleri koriste ovaj kontekst da svi ekrani rade nad istim podacima.
 */
public final class AppContext {

    private static final HotelService HOTEL_SERVICE = new HotelService();
    private static final GuestService GUEST_SERVICE = new GuestService();
    private static final ReservationService RESERVATION_SERVICE = new ReservationService();

    private static boolean loaded;

    private AppContext() {
    }

    public static HotelService hotels() {
        ensureLoaded();
        return HOTEL_SERVICE;
    }

    public static GuestService guests() {
        ensureLoaded();
        return GUEST_SERVICE;
    }

    public static ReservationService reservations() {
        ensureLoaded();
        return RESERVATION_SERVICE;
    }

    private static synchronized void ensureLoaded() {
        if (loaded) {
            return;
        }
        HOTEL_SERVICE.loadFromJson();
        GUEST_SERVICE.loadFromJson();
        RESERVATION_SERVICE.loadFromJson();
        loaded = true;
    }
}
