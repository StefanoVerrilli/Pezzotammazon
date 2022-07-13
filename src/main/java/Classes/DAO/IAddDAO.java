package Classes.DAO;

import java.sql.SQLException;

/**
 * Interfaccia DAO per l'aggiunta di elementi all'interno del database
 * @param <T> Tipo generico
 */
@FunctionalInterface
public interface IAddDAO<T>{

    boolean add(T t) throws SQLException;
}
