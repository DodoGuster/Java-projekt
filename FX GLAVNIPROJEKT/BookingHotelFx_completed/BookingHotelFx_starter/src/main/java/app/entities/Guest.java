package app.entities;


import java.io.Serializable;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Radenje gosta hotela i clana bookinga
 *
 */

public class Guest extends Person implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Guest.class);

    private String email;

    /** Default konstruktor za JSON-B. */
    public Guest() {
        super();
    }

    public Guest(String name, String surname, String email, BigDecimal age) {
       super(name,surname,age);
        this.email = email;

        log.info("Kreiran je novi gost: {} {}", name,surname);
    }

    // getAge/setAge nasljeđujemo iz Person
    public String getEmail() {
        log.info("Pozvano je vracanje emaila.");
        return email;
    }
    public void setEmail(String email) { this.email = email; }


    @Override
    public void isPerson() {
        log.info("Gost je osoba!");
    }
}

