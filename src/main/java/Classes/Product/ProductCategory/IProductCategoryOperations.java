package Classes.Product.ProductCategory;

import Classes.DAO.*;

public interface IProductCategoryOperations<T> extends DAO, IAddDAO<T>, IDeleteDAO, IUpdateDAO<T>, IGetDAO<T> {


}
