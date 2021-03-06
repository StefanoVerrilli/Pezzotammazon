package Classes.ShoppingItem;

import Classes.DAO.DAO;
import Classes.DAO.IUpdateDAO;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Contiene le operazioni da eseguire sul carrello.
 * @param <T> Tipo generico.
 */

public interface IShoppingItemOperations<T> extends IUpdateDAO<T> {

    Optional<T> get(Integer productId, Integer UserID) throws SQLException;

    void delete(Integer ProductID, Integer UserID) throws SQLException;

    boolean add(ShoppingItemModel item, Integer UserID) throws SQLException;

}
