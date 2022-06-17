package Classes.Pattern;

import Classes.Product;
import Classes.ProductOperations;
import Classes.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProductsPageLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductOperations productOperations = new ProductOperations();
        List<Product> data = productOperations.getAll();
        request.getSession().setAttribute("data",data);
        User user = (User) request.getSession().getAttribute("user");
        return (user.getAccessType() == 1) ? "ProductsTable" : "UserProducts";
    }
}
