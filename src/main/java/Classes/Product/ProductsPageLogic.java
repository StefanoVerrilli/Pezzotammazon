package Classes.Product;

import Classes.Command.DiscriminatorProducts;
import Classes.Command.Dispatcher;
import Classes.Command.Invoker;
import Classes.FrontController.Action;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductsPageLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ProductModel> products = (List<ProductModel>) request.getSession().getAttribute("products_table");
        DiscriminatorProducts discriminatorProducts = new DiscriminatorProducts(products);
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        Invoker invoker = new Invoker(new Dispatcher(discriminatorProducts,user));
        return invoker.executeOperation();
    }


}
