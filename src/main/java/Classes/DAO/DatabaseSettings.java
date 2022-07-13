package Classes.DAO;

/**
 * Classe che imposta i valori delle variabili necessarie al corretto funzionamento del database
 * @see Database
 */
public class DatabaseSettings {
    /**
     * Percorso completo sul quale risiede il database
     */
    public final static String DB_PATH = "/home/francesco/Documenti/Pezzotammazon/db.sqlite";
    /**
     * Driver del database utilizzato
     */
    public final static String DB_DRIVER = "org.sqlite.JDBC";
    /**
     * Tipologia di database utilizzato
     */
    public final static String DB_TYPE = "sqlite";
}