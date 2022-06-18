package Classes.Pattern;

import Classes.Command.AdminInfo;
import Classes.Command.Discriminator;
import Classes.Command.Dispatcher;
import Classes.Command.UserInfo;
import Classes.Product;
import Classes.ProductOperations;
import Classes.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class Edit implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Dispatcher dispatcher = new Dispatcher();
        User user = (User) request.getSession().getAttribute("user");
        Optional<Product> product = null;
        ProductOperations productOperations = new ProductOperations();
        int id = Integer.parseInt(request.getParameter("id"));
        product = productOperations.get(id);
        request.getSession().setAttribute("Product",product.get());
        Discriminator discriminator = new Discriminator(id);
        if(user.getAccessType() == 1)
            return dispatcher.executeOperation(new AdminInfo(discriminator));
        else
            return dispatcher.executeOperation(new UserInfo(discriminator));
    }
}
