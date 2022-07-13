package Classes.Navbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Un builder per la creazione della navbar. Costruisce effettivamente la navbar secondo le direzioni del {@link Director}.
 * @see BuilderInterface
 */

public class NavBarBuilder implements BuilderInterface{

    /**
     * Contiene i dati della navbar.
     */
    private final Navbar myNavbar = new Navbar();
    /**
     * Contiene gli elementi della navbar.
     */
    private final List<Map.Entry<String, String>> elements = new ArrayList<>();

    /**
     * Aggiunge gli elementi in input nella composizione di elementi della navbar.
     * @param pair Map Entry stringa-stringa che contiene nome e link del collegamento della navbar.
     */
    @Override
    public void addLink(Map.Entry<String, String> pair) {
        this.elements.add(pair);
    }

    /**
     * Ritorna la navbar costruita.
     * @return Navbar completa di elementi aggiunti.
     */
    public Navbar getProduct(){
        myNavbar.setElements(elements);
        return myNavbar;
    }
}
