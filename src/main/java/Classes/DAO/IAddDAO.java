package Classes.DAO;

import java.sql.SQLException;

/**
 * Interfaccia DAO per l'aggiunta di elementi all'interno del database
 * @param <T> Tipo generico
 */

public interface IAddDAO<T>{

    public boolean add(T t) throws SQLException;
}
