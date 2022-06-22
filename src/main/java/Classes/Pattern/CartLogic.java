package Classes.Pattern;

import Classes.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class CartLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        CartOperation cartOperation = new CartOperation();
        Optional<Cart> Cart = cartOperation.get(user.getId());
        List<ShoppingItem> shoppingList = cartOperation.getAll(Cart.get().getCart_id());
        request.getSession().setAttribute("ShoppingList",shoppingList);
        return "Cart";
    }
}
