package Classes.DAO;

import java.sql.SQLException;

/**
 * Interfaccia DAO per l'eliminazione di elementi dal database
 */
@FunctionalInterface
public interface IDeleteDAO {
    void delete(Integer ID) throws SQLException;
}
