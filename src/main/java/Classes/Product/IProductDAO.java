package Classes.Product;

import Classes.DAO.*;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO<T> extends DAO,IAddDAO<T>, IDeleteDAO, IUpdateDAO<T>, IGetDAO<T> {

public List<T> getAllByCategory(Integer Category) throws SQLException;

}
