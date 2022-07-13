package Classes.Product;

import Classes.DAO.*;

import java.sql.SQLException;
import java.util.List;

public interface IProductOperations<T> extends IAddDAO<T>, IDeleteDAO, IUpdateDAO<T>, IGetDAO<T>,IGetAll<T> {
 List<T> getAllByCategory(Integer Category) throws SQLException;

 List<T> getAllByCategory(String CategoryDescription) throws SQLException;

}
