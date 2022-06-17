package Classes.Pattern;

import Classes.Order;
import Classes.OrdersOperations;
import Classes.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CartLogic implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        OrdersOperations ordersOperations = new OrdersOperations();
        List<Order> shoppingList = ordersOperations.getCart(user.getId());
        request.getSession().setAttribute("ShoppingList",shoppingList);
        return "Cart";
    }
}
