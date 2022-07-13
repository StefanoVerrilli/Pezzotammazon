package Classes.Navbar;

import java.util.Map;

/**
 * Interfaccia per il builder della navbar.
 * @see Navbar
 * @see NavBarBuilder
 */

public interface BuilderInterface {
    /**
     * Aggiunge un link alla navbar.
     * @param pair Map Entry stringa-stringa che contiene nome e link del collegamento della navbar.
     */
    void addLink(Map.Entry<String, String> pair);
}
