package Classes.Order;

import Classes.DAO.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO<T> extends DAO{

    List<Order> getAllByCategory(String Category, Integer collectionID) throws SQLException;
}
