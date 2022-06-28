package Classes.Product;

import Classes.DAO.IAddDAO;
import Classes.DAO.IDeleteDAO;
import Classes.DAO.IGetDAO;
import Classes.DAO.IUpdateDAO;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO<T> extends IAddDAO<T>, IDeleteDAO, IUpdateDAO<T>, IGetDAO<T> {

public List<T> getAllByCategory(String Category) throws SQLException;

}
