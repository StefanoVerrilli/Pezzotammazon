package Classes.Pattern;

import Classes.Order;
import Classes.ShoppingItem;
import Classes.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CartLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        ShoppingItem ordersOperations = new ShoppingItem();
        List<Order> shoppingList = ordersOperations.getCart(user.getId());
        request.getSession().setAttribute("ShoppingList",shoppingList);
        return "Cart";
    }
}
