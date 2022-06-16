package Classes.Pattern;

import Classes.ProductOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class delete implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String  id = request.getParameter("id");
        if(id != null){
            ProductOperations.DeleteProduct(Integer.parseInt(id));
            request.getSession().setAttribute("data",ProductOperations.GetProducts());
            return "ProductsTable";
        }else{
            return "";
        }
    }
}
