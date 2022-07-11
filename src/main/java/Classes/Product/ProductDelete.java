package Classes.Product;

import Classes.Exceptions.LogicException;
import Classes.FrontController.Action;
import Classes.Product.ProductCategory.ProductCategoriesOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDelete implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id;
        try{
            id = Integer.parseInt(request.getParameter("id"));}
        catch (NumberFormatException e){
            throw new LogicException(request,"error","error during fetching");
        }

        IProductOperations<ProductModel> productOperations =
        new ProductOperations(new ProductCategoriesOperations());
        productOperations.delete(id);
        request.getSession().setAttribute("products_table",productOperations.getAll());
        return "/AdminPages/ProductsTable";
    }
}
