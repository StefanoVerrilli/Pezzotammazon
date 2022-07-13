package Classes.OrderCollection;

import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Definisce le operazioni sull'aggiunta di storici di ordini.
 * @param <T>
 */

public interface IOrderCollectionOperations<T> extends IGetDAO<T>, IAddDAO<T> {

    boolean AddSingleOrders(int User_id) throws SQLException;

    List<T> getAll(int User_id) throws SQLException;
}
