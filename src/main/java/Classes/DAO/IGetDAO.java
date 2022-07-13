package Classes.DAO;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Interfaccia DAO per l'ottenimento di una quantità di dati dal database dato un parametro
 * @param <T> Tipo generico del dato da ottenere
 */
@FunctionalInterface
public interface IGetDAO<T> {
    Optional<T> get(Integer Id) throws SQLException;
}
