package Classes.Cart;

import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetAll;
import Classes.DAO.IGetDAO;
import Classes.ShoppingItem.ShoppingItemModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ICartOperation<T,G> extends DAO, IAddDAO<T>, IGetDAO<T> {
    public void EmptyCart(CartModel cart) throws SQLException;

    public List<ShoppingItemModel> getAll(Integer UserID) throws SQLException;



}
