package Classes.FrontController;

import Classes.Product.IProductOperations;
import Classes.Product.ProductCategory.IProductCategoryOperations;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductCategory.ProductCategoryModel;
import Classes.Product.ProductModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Pagina che gestisce l'operazione di inserimento di un prodotto (lato amministratore).
 */
public class InsertLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        IProductCategoryOperations<ProductCategoryModel> operation =
        new ProductCategoriesOperations();
        List<ProductCategoryModel> categories = operation.getAll();
        request.getSession().setAttribute("categories", categories);
        return "/AdminPages/InsertProduct";
    }
}
