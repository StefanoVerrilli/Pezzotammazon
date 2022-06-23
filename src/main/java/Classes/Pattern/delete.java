package Classes.Pattern;

import Classes.DAO.ProductOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class delete implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String  id = request.getParameter("id");
        if(id != null){
            ProductOperations productOperations = new ProductOperations();
            productOperations.delete(Integer.parseInt(id));
            request.getSession().setAttribute("data",productOperations.getAll());
            return "ProductsTable";
        }else{
            return "";
        }
    }
}
