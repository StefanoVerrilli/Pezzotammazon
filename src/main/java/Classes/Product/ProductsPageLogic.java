package Classes.Product;

import Classes.Command.DiscriminatorProducts;
import Classes.Command.Dispatcher;
import Classes.Command.Invoker;
import Classes.Pattern.Action;
import Classes.Product.ProductModel;
import Classes.Product.ProductOperations;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductsPageLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductOperations productOperations = new ProductOperations();
        List<ProductModel> data = productOperations.getAll();
        request.getSession().setAttribute("data",data);
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        DiscriminatorProducts discriminator = new DiscriminatorProducts(data);
        Invoker invoker = new Invoker(new Dispatcher(discriminator,user));
        return  invoker.executeOperation();
    }
}
