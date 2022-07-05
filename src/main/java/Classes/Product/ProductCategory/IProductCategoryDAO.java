package Classes.Product.ProductCategory;

import Classes.DAO.*;

public interface IProductCategoryDAO<T> extends DAO, IAddDAO<T>, IDeleteDAO, IUpdateDAO<T>, IGetDAO<T> {


}
