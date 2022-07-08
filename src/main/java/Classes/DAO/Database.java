package Classes.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Classes.DAO.DatabaseSettings.*;

/**
 * Classe singleton che implementa il database, richiede l'impostazione delle variabili necessarie a gestirlo
 * @see DatabaseSettings
 */

public class Database {
    private final static String JDBC_DRIVER = DB_DRIVER;
    private final static String DATABASE_URL = "jdbc:" + DB_TYPE + ":"+ DB_PATH;

    /**
     * Variabile di sessione di connessione al database
     * @see Connection
     */
    private Connection conn;

    /**
     * Istanza che contiene l'oggetto Database
     */
    private static Database dbInstance;

    /**
     * Costruttore privato, per impedire l'istanziazione della classe da parte di classi esterne
     */
    private Database(){
    }


    /**
     * Permette di ottenere l'istanza privata del singleton Database
     * @return Unica istanza della classe Database
     */
    public static Database getInstance() {
        /**
         * Costruisce la classe se non ancora istanziata
         */
        if (dbInstance == null) {
            dbInstance = new Database();
        }
        return dbInstance;
    }

    /**
     * Permette la connessione al database
     * @return Sessione di connessione al database
     * @see Connection
     */
    public Connection getConnection() {

        if (conn == null) {
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DATABASE_URL);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

}
