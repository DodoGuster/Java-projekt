package app.entities;

import java.math.BigDecimal;

/**
 * Baca gresku za krivo unesenog gosta hotela
 */
public class InvalidGuestDataException extends Exception {

    public InvalidGuestDataException(
            String name,
            String surname,
            String email,
            BigDecimal age,
            String reason
    ) {
        super(String.format(
                "Neispravan gost: %s %s (email: %s, dob: %s) – %s",
                name, surname, email, age, reason
        ));
    }
}
