package Classes.DAO;

import java.util.List;
import java.sql.SQLException;

/**
 * Interfaccia DAO per l'ottenimento di tutti gli elementi di una data quantità di dati presenti nel database
 * @param <T> Tipo generico
 */
@FunctionalInterface
public interface IGetAll<T>{

    List<T> getAll() throws SQLException;
}
