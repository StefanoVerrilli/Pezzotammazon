package Classes.Pattern;

import Classes.Product;
import Classes.ProductOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class Edit implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String Id = request.getParameter("id");
        Optional<Product> product = null;
        if(Id != null){
            ProductOperations productOperations = new ProductOperations();
            product = productOperations.get(Integer.parseInt(Id));
            request.getSession().setAttribute("Product",product.get());
            return "Edit";
        }else{
            return "ProductsTable";
        }
    }
}
