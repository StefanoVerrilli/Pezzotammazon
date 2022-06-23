package Classes.Pattern;

import Classes.Command.Discriminator;
import Classes.Command.Invoker;
import Classes.Command.Dispatcher;
import Classes.Models.Product;
import Classes.DAO.ProductOperations;
import Classes.Models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class FetchProduct implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        Optional<Product> product;
        ProductOperations productOperations = new ProductOperations();
        Integer id = Integer.parseInt(request.getParameter("id"));
        product = productOperations.get(id);
        if(!product.isPresent()){
            request.getSession().setAttribute("error","Error during fetching of the product");
            return "/";
        }
        request.getSession().setAttribute("Product",product.get());
        Discriminator discriminator = new Discriminator(id);
        Invoker dispatcher = new Invoker(new Dispatcher(discriminator,user));
        return dispatcher.executeOperation();
    }
}
