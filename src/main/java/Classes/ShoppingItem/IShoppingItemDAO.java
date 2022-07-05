package Classes.ShoppingItem;

import Classes.DAO.DAO;
import Classes.DAO.IUpdateDAO;

import java.sql.SQLException;
import java.util.Optional;

public interface IShoppingItemDAO<T> extends DAO, IUpdateDAO<T> {

    Optional<ShoppingItemModel> get(Integer productId, Integer UserID) throws SQLException;


}
