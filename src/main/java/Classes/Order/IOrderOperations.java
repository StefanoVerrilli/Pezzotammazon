package Classes.Order;

import Classes.DAO.DAO;
import Classes.DAO.Database;

import java.sql.SQLException;
import java.util.List;

public interface IOrderOperations<T>{

    List<T> getAllByCategory(String Category, Integer collectionID) throws SQLException;

    List<T> getAll(Integer collectionID) throws SQLException;
}
