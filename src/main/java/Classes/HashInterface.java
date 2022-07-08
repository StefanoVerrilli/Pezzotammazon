package Classes;

/**
 * Interfaccia che devono implementare le classi che generano stringhe di hashing data una stringa come valore, serve a implementare la sicurezza del database, codificando le password inserite dagli utenti
 */

public interface HashInterface {

    /**
     * Genera una stringa hash dalla chiave passata in input
     * @param Key Stringa che rappresenta la chiave di cui si vuole ottenere la versione hash
     * @return Versione hash della chiave passata in input
     */
    public String HashValue(String Key);
}
