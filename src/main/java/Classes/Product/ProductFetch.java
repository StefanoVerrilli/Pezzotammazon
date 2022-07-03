package Classes.Product;

import Classes.Command.Discriminator;
import Classes.Command.Invoker;
import Classes.Command.Dispatcher;
import Classes.FrontController.Action;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ProductFetch implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        Optional<ProductModel> product;
        ProductOperations productOperations = new ProductOperations(new ProductCategoriesOperations());
        Integer id = Integer.parseInt(request.getParameter("id"));
        product = productOperations.get(id);
        if(!product.isPresent()){
            request.getSession().setAttribute("error","Error during fetching of the product");
            return "/Homepage.jsp";
        }

        ProductCategoriesOperations operation = new ProductCategoriesOperations();
        List<ProductCategoryModel> categories = operation.getAll();
        request.getSession().setAttribute("categories", categories);


        request.getSession().setAttribute("Product",product.get());
        Discriminator discriminator = new Discriminator(id);
        Invoker dispatcher = new Invoker(new Dispatcher(discriminator,user));
        return dispatcher.executeOperation();
    }
}
