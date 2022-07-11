package Classes.Product;

import Classes.FrontController.Action;
import Classes.Product.ProductCategory.ProductCategoriesOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDelete implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String  id = request.getParameter("id");
        if(id != null){
            IProductOperations<ProductModel> productOperations =
            new ProductOperations(new ProductCategoriesOperations());
            productOperations.delete(Integer.parseInt(id));
            request.getSession().setAttribute("products_table",productOperations.getAll());
            return "/AdminPages/ProductsTable";
        }else{
            return "";
        }
    }
}
