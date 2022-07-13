package Classes.Cart;

import Classes.FrontController.Action;
import Classes.ShoppingItem.ShoppingItemModel;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Servlet per la visualizzazione grafica del carrello.
 */

public class CartLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        ICartOperations<CartModel,ShoppingItemModel> cartOperation = new CartOperations();
        List<ShoppingItemModel> shoppingList = cartOperation.getAll(user.getId());
        request.getSession().setAttribute("ShoppingList",shoppingList);
        return "/UserPages/Cart";
    }
}
