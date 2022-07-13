package Classes.Product;

import Classes.Command.DiscriminatorProducts;
import Classes.Command.Dispatcher;
import Classes.Command.Invoker;
import Classes.FrontController.Action;
import Classes.Product.ProductCategory.ProductCategoriesOperations;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Contiene la logica per la generazione della pagina dei prodotti
 */

public class ProductsPageLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        IProductOperations<ProductModel> operations =
        new ProductOperations(new ProductCategoriesOperations());
        List<ProductModel> products = operations.getAll();
        request.getSession().setAttribute("products_table", products);

        /**
         * Reindirizza alla pagina corretta a seconda del tipo di utente.
         * @see Dispatcher
         * @see Invoker
         * @see Classes.User.AccessLevels
         * @see DiscriminatorProducts
         */
        DiscriminatorProducts discriminatorProducts = new DiscriminatorProducts(products);
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        Invoker invoker = new Invoker(new Dispatcher(discriminatorProducts,user));
        return invoker.executeOperation();
    }


}
