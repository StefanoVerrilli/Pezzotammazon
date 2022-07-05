package Classes.FrontController;

import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.Product.ProductCategory.ProductCategoryModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class InsertLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProductCategoriesOperations operation = new ProductCategoriesOperations();
        List<ProductCategoryModel> categories = operation.getAll();
        request.getSession().setAttribute("categories", categories);
        return "/AdminPages/InsertProduct";
    }
}
