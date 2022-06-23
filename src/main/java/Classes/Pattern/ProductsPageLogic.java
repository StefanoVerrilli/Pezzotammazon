package Classes.Pattern;

import Classes.Command.DiscriminatorProducts;
import Classes.Command.Dispatcher;
import Classes.Command.Invoker;
import Classes.Models.Product;
import Classes.DAO.ProductOperations;
import Classes.Models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductsPageLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductOperations productOperations = new ProductOperations();
        List<Product> data = productOperations.getAll();
        request.getSession().setAttribute("data",data);
        User user = (User) request.getSession().getAttribute("user");
        DiscriminatorProducts discriminator = new DiscriminatorProducts(data);
        Invoker invoker = new Invoker(new Dispatcher(discriminator,user));
        return  invoker.executeOperation();
    }
}
