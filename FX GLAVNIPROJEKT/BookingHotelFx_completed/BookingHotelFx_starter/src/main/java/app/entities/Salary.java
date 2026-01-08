package app.entities;


import jakarta.json.bind.annotation.JsonbProperty;

import java.math.BigDecimal;

/**
 * Spremanje placa zaposlenika
 *
 * @param salary
 */

public record Salary(@JsonbProperty("amount") BigDecimal salary) {
}
