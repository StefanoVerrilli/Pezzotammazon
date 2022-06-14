package Classes.Pattern;

import Classes.Product;
import Classes.ProductOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProductsPageLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Product> data = new ArrayList<>();
        data = ProductOperations.GetProducts();
        request.getSession().setAttribute("data",data);
        return "ProductsTable";
    }
}
