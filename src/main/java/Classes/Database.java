package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Classes.DatabaseSettings.*;

public class Database {
    private final static String JDBC_DRIVER = DB_DRIVER;
    private final static String DATABASE_URL = "jdbc:" + DB_TYPE + ":"+ DB_PATH;
    private Connection conn;
    private static Database dbIsntance;

    private Database(){
    }


    public static Database getInstance() {
        if (dbIsntance == null) {
            dbIsntance = new Database();
        }
        return dbIsntance;
    }

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
