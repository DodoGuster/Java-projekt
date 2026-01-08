package com.dominik.service;

import app.entities.Guest;
import app.entities.services.GuestService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public class GuestServiceFx {

    private final GuestService guestService = AppContext.guests();

    public List<Guest> findAll() {
        return guestService.getAll();
    }

    public List<Guest> search(String name, String surname, String email,
                              Integer minAge, Integer maxAge) {
        return findAll().stream()
                .filter(g -> containsIgnoreCase(g.getName(), name))
                .filter(g -> containsIgnoreCase(g.getSurname(), surname))
                .filter(g -> containsIgnoreCase(g.getEmail(), email))
                .filter(g -> ageInRange(g.getAge(), minAge, maxAge))
                .toList();
    }

    private boolean ageInRange(BigDecimal age, Integer minAge, Integer maxAge) {
        if (age == null) {
            return false;
        }
        int a = age.intValue();
        if (minAge != null && a < minAge) return false;
        return maxAge == null || a <= maxAge;
    }

    private boolean containsIgnoreCase(String value, String query) {
        if (query == null || query.trim().isEmpty()) return true;
        if (value == null) return false;

        String v = value.toLowerCase(Locale.ROOT);
        String q = query.trim().toLowerCase(Locale.ROOT);
        return v.contains(q);
    }
}
