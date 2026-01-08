package app.entities.util;

import java.util.Collection;
import java.util.List;

import static app.entities.Staff.log;

public final class PrintUtils {

    private PrintUtils() {
    }

    /**
     * Generički ispis za bilo koji tip T.
     * Koristi upper bounded wildcard (? extends T)
     * kako bi metoda primila i podtipove T.
     */
    public static <T> void printList(Collection<? extends T> items) {
        log.info("Ispis liste:");

        for (T item : items) {
            log.info("{}", item);
        }

        log.info("-------------------------");
    }

    /**
     * Overload čisto zbog lakoće korištenja:
     * prima List<T> i prosljeđuje na glavnu wildcard metodu.
     */
    public static <T> void printList(List<T> items) {
        printList((Collection<? extends T>) items);
    }
}
