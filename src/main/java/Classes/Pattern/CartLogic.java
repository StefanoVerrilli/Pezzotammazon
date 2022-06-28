package Classes.Pattern;

import Classes.DAO.CartOperation;
import Classes.Models.Cart;
import Classes.Models.ShoppingItem;
import Classes.Models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class CartLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        CartOperation cartOperation = new CartOperation(user.getId());
        List<ShoppingItem> shoppingList = cartOperation.getAll();
        request.getSession().setAttribute("ShoppingList",shoppingList);
        return "/UserPages/Cart";
    }
}
