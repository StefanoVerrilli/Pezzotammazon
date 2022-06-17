package Classes.Pattern;

import Classes.Order;
import Classes.OrdersOperations;
import Classes.User;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@MultipartConfig(maxFileSize = 16177215)
public class ChangeCost implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int quantity = Integer.parseInt(request.getParameter("orderQuantity"));
        int id = Integer.parseInt(request.getParameter("orderId"));
        OrdersOperations ordersOperations = new OrdersOperations();
        Order order = ordersOperations.get(id).get();
        System.out.println(order.getQuantity());
        order.setQuantity(quantity);
        ordersOperations.update(order);
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("ShoppingList",ordersOperations.getCart(user.getId()));
        return "Cart";
    }
}
