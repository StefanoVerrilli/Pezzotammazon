package Classes.Product;

import Classes.FrontController.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDelete implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String  id = request.getParameter("id");
        if(id != null){
            ProductOperations productOperations = new ProductOperations(new ProductCategoriesOperations());
            productOperations.delete(Integer.parseInt(id));
            request.getSession().setAttribute("data",productOperations.getAll());
            return "/AdminPages/ProductsTable";
        }else{
            return "";
        }
    }
}
