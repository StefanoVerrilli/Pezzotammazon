package Classes.Order;

import Classes.DAO.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IOrderOperations<T> extends DAO{

    List<T> getAllByCategory(String Category, Integer collectionID) throws SQLException;
}
