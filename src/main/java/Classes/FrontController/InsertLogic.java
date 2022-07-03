package Classes.FrontController;

import Classes.Product.ProductCategoriesOperations;
import Classes.Product.ProductCategoryModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class InsertLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProductCategoriesOperations operation = new ProductCategoriesOperations();
        List<ProductCategoryModel> categories = operation.getAll();
        request.getSession().setAttribute("categories", categories);
        System.out.println(categories.size());
        return "/AdminPages/InsertProduct";
    }
}
