package app.entities;


import java.math.BigDecimal;

/**
 * Stavlja minimalnu placu za radnike hotela
 */

public interface Payable {
    boolean minPayment(BigDecimal minimalna);
}


