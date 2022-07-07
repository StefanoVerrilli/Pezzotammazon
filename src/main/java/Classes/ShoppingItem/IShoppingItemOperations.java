package Classes.ShoppingItem;

import Classes.DAO.DAO;
import Classes.DAO.IUpdateDAO;

import java.sql.SQLException;
import java.util.Optional;

public interface IShoppingItemOperations<T> extends DAO, IUpdateDAO<T> {

    Optional<T> get(Integer productId, Integer UserID) throws SQLException;


}
