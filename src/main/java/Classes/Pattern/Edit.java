package Classes.Pattern;

import Classes.Product;
import Classes.ProductOperations;
import Servlets.ProductPageLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Edit implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String Id = request.getParameter("id");
        Product product = null;
        if(Id != null){
            product = ProductOperations.GetSpecificProduct(Integer.parseInt(Id));
            request.getSession().setAttribute("Product",product);
            return "Edit";
        }else{
            return "ProductsTable";
        }
    }
}
