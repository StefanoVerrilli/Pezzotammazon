package Classes.Product.ProductCategory;

import Classes.DAO.*;

import java.sql.SQLException;

public interface IProductCategoryOperations<T> extends DAO, IAddDAO<T>, IDeleteDAO, IUpdateDAO<T>,
 IGetDAO<T>,IGetAll<T> {
    int getCount() throws SQLException;
}
