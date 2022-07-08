package Classes.DAO;

import java.sql.SQLException;

/**
 * Interfaccia DAO per l'eliminazione di elementi dal database
 */

public interface IDeleteDAO {
    public void delete(Integer ID) throws SQLException;
}
