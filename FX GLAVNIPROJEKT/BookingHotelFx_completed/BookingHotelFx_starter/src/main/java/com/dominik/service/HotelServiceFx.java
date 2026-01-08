package com.dominik.service;

import app.entities.Hotel;
import app.entities.services.HotelService;

import java.util.List;
import java.util.Locale;

public class HotelServiceFx {

    private final HotelService hotelService = AppContext.hotels();

    public List<Hotel> findAll() {
        return hotelService.getAll();
    }

    public List<Hotel> search(String name, String city, String type) {
        return findAll().stream()
                .filter(h -> containsIgnoreCase(h.getName(), name))
                .filter(h -> containsIgnoreCase(String.valueOf(h.getCity()), city))
                .filter(h -> containsIgnoreCase(h.getType().name(), type))
                .toList();
    }

    private boolean containsIgnoreCase(String value, String query) {
        if (query == null || query.trim().isEmpty()) return true;
        if (value == null) return false;

        String v = value.toLowerCase(Locale.ROOT);
        String q = query.trim().toLowerCase(Locale.ROOT);
        return v.contains(q);
    }
}
