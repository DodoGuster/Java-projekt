package app.entities;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stvara zaposlenike u hotelu
 *
 * @see Payable
 * @see Hotel
 */

public class Staff extends Person implements Payable {
    public static final Logger log = LoggerFactory.getLogger(Staff.class);

    private String role;
    private Salary salary;

    /** Default konstruktor za JSON-B. */
    public Staff() {
        super();
    }

    public Staff(String name, String surname, String role, Salary salary, BigDecimal age) {
        super(name, surname, age);
        this.role = role;
        this.salary = salary;

        log.debug("Staff created");
        log.info("Kreiran objekt staff: {} {} {} {} {}",name, surname, role, age, salary);
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Salary getSalary() { return salary; }
    public void setSalary(Salary salary) { this.salary = salary; }

    @Override
    public void isPerson() {
       log.info("Osoba je član osoblja (staff).");
    }

    @Override
    public boolean minPayment(BigDecimal minimalna) {
        return salary.salary().compareTo(minimalna) >= 0;
    }

}
