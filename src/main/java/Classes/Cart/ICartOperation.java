package Classes.Cart;

import Classes.DAO.IAddDAO;
import Classes.DAO.IGetAll;

import java.sql.SQLException;
import java.util.Optional;

public interface ICartOperation<T,G> extends IGetAll<G>, IAddDAO<T> {
    public Optional<T> getCart() throws SQLException;
    public void EmptyCart() throws SQLException;



}
