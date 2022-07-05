package Classes.Cart;

import Classes.FrontController.Action;
import Classes.ShoppingItem.ShoppingItemModel;
import Classes.User.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CartLogic implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        CartOperation cartOperation = new CartOperation();
        List<ShoppingItemModel> shoppingList = cartOperation.getAll(user.getId());
        request.getSession().setAttribute("ShoppingList",shoppingList);
        return "/UserPages/Cart";
    }
}
