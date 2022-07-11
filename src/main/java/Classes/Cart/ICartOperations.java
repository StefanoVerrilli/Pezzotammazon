package Classes.Cart;

import Classes.DAO.DAO;
import Classes.DAO.IAddDAO;
import Classes.DAO.IGetDAO;

import java.sql.SQLException;
import java.util.List;

public interface ICartOperations<T,G> extends DAO, IAddDAO<T>, IGetDAO<T> {
    void EmptyCart(T cart) throws SQLException;

    List<G> getAll(Integer UserID) throws SQLException;

    void delete(Integer cartId, Integer productId) throws SQLException;


}
