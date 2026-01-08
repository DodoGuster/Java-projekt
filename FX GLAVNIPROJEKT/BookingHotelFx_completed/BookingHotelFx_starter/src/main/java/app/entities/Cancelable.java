package app.entities;


/**
 * Omogucava otkzavianje rezervacije hotela
 *
 * @return true ako se moze otkazazi, false ako se ne moze
 */

public sealed interface Cancelable permits Hotel{
    boolean canBeCancelled();
}
