package Classes.DAO;

import java.sql.SQLException;

/**
 * Interfaccia DAO per l'aggiornamento di dati presenti sul database
 * @param <T> Tipo generico
 */

public interface IUpdateDAO<T> {
    public void update(T t) throws SQLException;

}
