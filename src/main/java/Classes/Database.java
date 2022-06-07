package Classes;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private final static String JDBC_DRIVER="org.sqlite.JDBC";
    private final static String DATABASE_URL = "jdbc:sqlite:/Users/stefanoverrilli/IdeaProjects/UserLogin/db.sqlite";
    private Connection conn;
    private static Database dbIsntance;

    private Database(){
    }


    public static Database getInstance() {
        if (dbIsntance == null) {
            System.out.println("instance");
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
