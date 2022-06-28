package Classes.Pattern;

import Classes.DAO.CartOperation;
import Classes.DAO.ProductOperations;
import Classes.DAO.ShoppingItemOperations;
import Classes.Models.Cart;
import Classes.Models.Product;
import Classes.Models.ShoppingItem;
import Classes.Models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddCart implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));
        if(id == null) {
            request.getSession().setAttribute("error", "Invalid Product, please retry");
            return "UserProducts";
        }
        User user = (User) request.getSession().getAttribute("user");
        ProductOperations productOperations = new ProductOperations();
        CartOperation cartOperation = new CartOperation(user.getId());

        ShoppingItemOperations shoppingItemOperations = new ShoppingItemOperations(cartOperation);
        Product newProduct = productOperations.get(id).get();
        ShoppingItem newOrder = new ShoppingItem(newProduct,cartOperation.getNow().get().getCart_id());
        shoppingItemOperations.add(newOrder);
        request.getSession().setAttribute("ShoppingList",cartOperation.getAll());
        return "/UserPages/Cart";
    }
}
