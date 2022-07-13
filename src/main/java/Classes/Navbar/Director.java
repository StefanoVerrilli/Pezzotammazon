package Classes.Navbar;

import java.util.AbstractMap;

/**
 * Dirige le operazioni di costruzione della navbar.
 */

public class Director {

    /**
     * Builder da utilizzare per la costruzione
     */
    private final BuilderInterface Builder;

    /**
     * Costruisce la classe {@link Director}.
     * @param builder Tipo di {@link BuilderInterface} da utilizzare per la costruzione della navbar.
     */
    public Director(BuilderInterface builder) {
        this.Builder = builder;
    }

    /**
     * Costruisce la navbar per l'amministratore.
     * @see BuilderInterface
     */
    public void constructAdminNavBar(){
        Builder.addLink(new AbstractMap.SimpleEntry<>("Insert a new product", "InsertLogic.do"));
        Builder.addLink(new AbstractMap.SimpleEntry<>("View products table","ProductsTable.do"));
        Builder.addLink(new AbstractMap.SimpleEntry<>("User purchases analysis","UsersPageLogic.do"));
        Builder.addLink(new AbstractMap.SimpleEntry<>("User macro-groups","Kmeans.do"));
    }

    /**
     * Costruisce la navbar per il cliente.
     * @see BuilderInterface
     */
    public void constructUserNavBar(){
        Builder.addLink(new AbstractMap.SimpleEntry<>("Shopping cart","CartLogic.do"));
        Builder.addLink(new AbstractMap.SimpleEntry<>("Explore all products","ProductsTable.do"));
    }

}
